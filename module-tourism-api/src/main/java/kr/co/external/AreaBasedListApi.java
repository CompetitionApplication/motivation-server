package kr.co.external;

import kr.co.dto.AreaBasedListResponse;
import kr.co.entity.TourismApi;
import kr.co.repository.TourismApiRepository;
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
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class AreaBasedListApi {

    @Value("${api.url.kor-tourism-api}")
    private String korUrl;
    @Value("${api.url.eng-tourism-api}")
    private String engUrl;
    @Value("${api.url.jpn-tourism-api}")
    private String jpnUrl;
    @Value("${api.url.chs-tourism-api}")
    private String chsUrl;


    private final TourismApiRepository tourismApiRepository;

    @Transactional
    public void tourismApi() throws URISyntaxException {
        //기존 데이터 초기화
        List<TourismApi> tourismApis = tourismApiRepository.findAll();
        tourismApis.forEach(tourismApi -> {
            tourismApi.delete();
        });


        List<String> countryUrl = List.of(korUrl, engUrl, chsUrl, jpnUrl);
        List<String> country = List.of("KOR", "ENG", "CHS", "JPN");

        for (int i = 0; i < countryUrl.size(); i++) {
            RestTemplate restTemplate = new RestTemplate();


            URI uri = new URI(countryUrl.get(i));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", "application/json");  // JSON 응답을 수락하도록 설정

            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<AreaBasedListResponse> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    AreaBasedListResponse.class
            );

            AreaBasedListResponse areaBasedListResponse = responseEntity.getBody();

            // 응답 처리
            int finalI = i;
            areaBasedListResponse.getResponse().getBody().getItems().getItem().forEach(item -> {
                tourismApiRepository.save(TourismApi.builder()
                        .cat1(item.getCat1())
                        .cat2(item.getCat2())
                        .cat3(item.getCat3())
                        .contentid(item.getContentid())
                        .contenttypeid(item.getContenttypeid())
                        .createdtime(item.getCreatedtime())
                        .mapx(item.getMapx())
                        .mapy(item.getMapy())
                        .mlevel(item.getMlevel())
                        .modifiedtime(item.getModifiedtime())
                        .sigungucode(item.getSigungucode())
                        .tel(item.getTel())
                        .title(item.getTitle())
                        .zipcode(item.getZipcode())
                        .addr1(item.getAddr1())
                        .addr2(item.getAddr2())
                        .areacode(item.getAreacode())
                        .booktour(item.getBooktour())
                        .firstimage(item.getFirstimage())
                        .firstimage2(item.getFirstimage2())
                        .cpyrhtDivCd(item.getCpyrhtDivCd())
                        .country(country.get(finalI))
                        .build());
            });
        }
    }
}

