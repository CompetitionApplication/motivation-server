package kr.co.client;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import kr.co.dto.web.openApiClient.response.FarmClientResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class OpenApiClient implements InitializingBean {

    private WebClient webClient;

    @Value("${open-api.key}")
    private String key;

    @Override
    public void afterPropertiesSet () {

        webClient = WebClient.builder()
                .baseUrl("http://211.237.50.150:7080")
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(
                        TcpClient.create()
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 30000)
                                .doOnConnected(connection -> {
                                    connection.addHandlerLast(new ReadTimeoutHandler(30000, TimeUnit.MILLISECONDS));
                                    connection.addHandlerLast(new WriteTimeoutHandler(30000, TimeUnit.MILLISECONDS));
                                })
                )))
                .build();
    }

    public FarmClientResDto openApi(String area){
        FarmClientResDto farmClientResDto = new FarmClientResDto();
        try {
            farmClientResDto = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/openapi/"+key+"/json/Grid_20150407000000000218_1/1/100")
                            .queryParam("AREA", area)
                            .build())
                    .retrieve()
                    .bodyToMono(FarmClientResDto.class)
                    .block();

        } catch (WebClientResponseException we) {
            System.out.println("======> error ::: " + we.getStatusCode().toString());
        } catch (Exception e) {
            System.out.println("======> error ::: " + e.getMessage());
        }

        return farmClientResDto;

    }
}
