package kr.co.controller.external;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.external.AreaBasedListApi;
import kr.co.external.AreaCodeApi;
import kr.co.external.DetailAreaCodeApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@Tag(name = "공공데이터 요청 API", description = "공공데이터 요청 API")
@RestController
@RequiredArgsConstructor
public class ExternalController {

    private final AreaCodeApi areaCodeApi;
    private final AreaBasedListApi areaBasedListApi;
    private final DetailAreaCodeApi detailAreaCodeApi;

    @Operation(summary = "지역코드 API", description = "지역코드 API 입니다. (건들지마쇼)")
    @PostMapping("/api/v1/external/area-code")
    public void areaCode() throws URISyntaxException {
        areaCodeApi.areaCode();
    }
    @Operation(summary = "관광 리스트 API", description = "관광 리스트 API 입니다. (건들지마쇼)")
    @PostMapping("/api/v1/external/tourism-api")
    public void tourismApi() throws URISyntaxException {
        areaBasedListApi.tourismApi();
    }
    @Operation(summary = "지역코드 상세리스트 API", description = "지역코드 상세리스트 API 입니다. (건들지마쇼)")
    @PostMapping("/api/v1/external/detail-area-code")
    public void detailAreaCode() throws URISyntaxException {
        detailAreaCodeApi.detailAreaCode();
    }


}
