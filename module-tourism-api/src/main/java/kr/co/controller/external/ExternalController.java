package kr.co.controller.external;

import kr.co.external.AreaBasedListApi;
import kr.co.external.AreaCodeApi;
import kr.co.external.CategoryCodeApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class ExternalController {

    private final AreaCodeApi areaCodeApi;
    private final CategoryCodeApi categoryCodeApi;
    private final AreaBasedListApi areaBasedListApi;

    @PostMapping("/api/v1/external/area-code")
    public void areaCode() throws URISyntaxException {
        areaCodeApi.areaCode();
    }
    @PostMapping("/api/v1/external/category-code")
    public void categoryCode() throws URISyntaxException {
        categoryCodeApi.categoryCode();
    }
    @PostMapping("/api/v1/external/tourism-api")
    public void tourismApi() throws URISyntaxException {
        areaBasedListApi.tourismApi();
    }

}
