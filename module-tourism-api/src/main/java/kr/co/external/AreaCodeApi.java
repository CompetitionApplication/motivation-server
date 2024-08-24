package kr.co.external;

import kr.co.dto.AreaCodeResponse;
import kr.co.entity.AreaCode;
import kr.co.repository.AreaCodeRepository;
import kr.co.repository.CategoryCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class AreaCodeApi {

    @Value("${api.url.kor-area-code}")
    private String korUrl;

    @Value("${api.url.eng-area-code}")
    private String engUrl;

    @Value("${api.url.chs-area-code}")
    private String chsUrl;

    @Value("${api.url.jpn-area-code}")
    private String jpnUrl;

    private final AreaCodeRepository areaCodeRepository;

    public void areaCode() throws URISyntaxException {
        List<String> countryUrl = List.of(korUrl, engUrl, chsUrl, jpnUrl);
        List<String> country = List.of("KOR", "ENG", "CHS", "JPN");

        for (int i = 0; i < countryUrl.size(); i++) {
            RestTemplate restTemplate = new RestTemplate();

            URI uri = new URI(countryUrl.get(i));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json");  // JSON 응답을 수락하도록 설정

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<AreaCodeResponse> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    AreaCodeResponse.class
            );

            AreaCodeResponse areaCodeResponse = responseEntity.getBody();

            int finalI = i;
            areaCodeResponse.getResponse().getBody().getItems().getItem().forEach(item -> {
                    areaCodeRepository.save(AreaCode.builder()
                            .code(item.getCode())
                            .name(item.getName())
                            .country(country.get(finalI))
                            .build());
                });
        }
    }
    }

