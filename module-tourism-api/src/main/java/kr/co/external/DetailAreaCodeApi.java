package kr.co.external;

import kr.co.dto.AreaCodeResponse;
import kr.co.entity.AreaCode;
import kr.co.entity.DetailAreaCode;
import kr.co.repository.AreaCodeRepository;
import kr.co.repository.DetailAreaCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class DetailAreaCodeApi {

    @Value("${api.url.kor-detail-area-code}")
    private String korUrl;

    @Value("${api.url.eng-detail-area-code}")
    private String engUrl;

    @Value("${api.url.chs-detail-area-code}")
    private String chsUrl;

    @Value("${api.url.jpn-detail-area-code}")
    private String jpnUrl;

    private final DetailAreaCodeRepository detailAreaCodeRepository;
    private final AreaCodeRepository areaCodeRepository;

    @Transactional
    public void detailAreaCode() throws URISyntaxException {
        List<String> countryUrl = List.of(korUrl, engUrl, chsUrl, jpnUrl);
        List<String> country = List.of("KOR", "ENG", "CHS", "JPN");
        //기존 데이터 초기화
        List<DetailAreaCode> detailAreaCodes = detailAreaCodeRepository.findAll();
        detailAreaCodes.forEach(detailAreaCode -> {
            detailAreaCode.delete();
        });


        for (int i = 0; i < countryUrl.size(); i++) {

            List<AreaCode> areaCodes = areaCodeRepository.findAllByCountry(country.get(i));
            for (AreaCode areaCode : areaCodes) {
                RestTemplate restTemplate = new RestTemplate();

                URI uri = new URI(countryUrl.get(i) + areaCode.getCode());

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
                    detailAreaCodeRepository.save(DetailAreaCode.builder()
                            .code(item.getCode())
                            .name(item.getName())
                            .country(country.get(finalI))
                            .areaCode(areaCode)
                            .build());
                });
            }
        }
    }
}

