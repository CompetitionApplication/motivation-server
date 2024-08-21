package kr.co.service.External;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Service
@Slf4j
public class TourismService {

    public void saveKoreaTourism() {
        RestTemplate restTemplate = new RestTemplate();

        // 디코딩된 serviceKey 값
        String decodedServiceKey = "lGGV/D0A3uplXyqNxUeWWMAiOMTTX3Lrxh0jK6zJRv3vFYB8qtaLk/qo/+p0RiVerTZ3pCeRFA8SmKiVn3Af7g==";

        // serviceKey URL 인코딩
        String encodedServiceKey;
        try {
            encodedServiceKey = URLEncoder.encode(decodedServiceKey, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Encoding error", e);
            return; // 인코딩 오류 발생 시 메서드 종료
        }

        // URL 구성
        String url = "https://apis.data.go.kr/B551011/KorService1/areaCode1" +
                "?MobileOS=ETCS" +
                "&MobileApp=TEST" +
                "&_type=json" +
                "&serviceKey=" + encodedServiceKey;

        try {
            // API 요청
            String response = restTemplate.getForObject(url, String.class);
            log.info("API Response: {}", response);
        } catch (Exception e) {
            // 예외 처리
            log.error("Error during API request", e);
        }
    }
}
