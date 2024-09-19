package kr.co.controller.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.auth.TourismUser;
import kr.co.common.ObjectResult;
import kr.co.dto.GoodsResDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.home.request.HomeMainReqDto;
import kr.co.dto.app.home.request.OpenGoodsReqDto;
import kr.co.dto.app.home.response.MainResDto;
import kr.co.service.app.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "home", description = "홈")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/app/home")
@Slf4j
public class HomeController {

    private final HomeService homeService;

    @Operation(summary = "메인", description = "메인 리스트 조회입니다.")
    @GetMapping(value = "/main")
    public ResponseEntity<?> getMain(@RequestBody HomeMainReqDto homeMainReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(homeService.getMain(serviceUser, homeMainReqDto));
    }

    @Operation(summary = "굿즈", description = "구매가능/불가능 굿즈 리스트 입니다.")
    @GetMapping(value = "/open-goods")
    public ResponseEntity<?> getOpenGoods(@RequestBody OpenGoodsReqDto openGoodsReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(homeService.getPossibleBuy(openGoodsReqDto, serviceUser));
    }

    @Operation(summary = "뱃지", description = "획득한 뱃지 리스트 입니다.")
    @GetMapping(value = "/collect-badges")
    public ResponseEntity<?> getCollectBadges(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(homeService.getCollectBadges(serviceUser));
    }
}
