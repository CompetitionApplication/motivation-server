package kr.co.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import kr.co.dto.web.openApiClient.request.FarmClientReqDto;
import kr.co.dto.web.openApiClient.response.FarmClientResDto;
import kr.co.dto.web.openApiClient.response.FarmClientResDtoa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class OpenApiClient implements InitializingBean {

    private WebClient webClient;
    private ObjectMapper mapper;

    //TODO
    //@Value("${opena-api.key}")
    //private String key;

    @Override
    public void afterPropertiesSet () {

        webClient = WebClient.builder()
                .baseUrl("http://211.237.50.150:7080")
                //.defaultHeader(HttpHeaders.ACCEPT, MediaType.ALL_VALUE) //응답 받을 타입
                //.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //응답 받을 타입
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

    public void test(){
        //TODO
        FarmClientReqDto farmClientReqDto = new FarmClientReqDto();

        FarmClientResDto farmClientResDto = new FarmClientResDto();
        FarmClientResDto aa;
        try {
            aa = webClient.get()
                    //.uri(uriBuilder -> uriBuilder.path("/openapi/"+key+"/json/Grid_20150407000000000218_1/1/5")
                    .uri(uriBuilder -> uriBuilder.path("/openapi/sample/json/Grid_20150407000000000218_1/1/5")
                            .queryParam("AREA", "충청")
                            .build())
                    .retrieve()
                    .bodyToMono(FarmClientResDto.class)
                    .block();

            ObjectMapper objectMapper = new ObjectMapper();
            //FarmClientResDto tt = objectMapper.readValue(aa,FarmClientResDto.class);
            log.info("result :{}", aa);

        } catch (WebClientResponseException we) {
            System.out.println("======> error ::: " + we.getStatusCode().toString());
        } catch (Exception e) {
            System.out.println("======> error ::: " + e.getMessage());
        }

    }
}
