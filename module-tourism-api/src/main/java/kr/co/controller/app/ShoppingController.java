package kr.co.controller.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.common.ObjectResult;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.shopping.request.ShoppingGoodsDetailReqDto;
import kr.co.dto.app.shopping.request.ShoppingGoodsKeepReqDto;
import kr.co.dto.app.shopping.request.ShoppingMainReqDto;
import kr.co.service.app.ShoppingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "shopping", description = "쇼핑")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/app/shopping")
@Slf4j
public class ShoppingController {

    private final ShoppingService shoppingService;

    @Operation(summary = "메인", description = "메인 리스트 조회입니다.")
    @PostMapping(value = "/main")
    public ResponseEntity<?> getMain(@RequestBody ShoppingMainReqDto shoppingMainReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(shoppingService.getMain(serviceUser,shoppingMainReqDto));
    }

    @Operation(summary = "굿즈 디테일", description = "굿즈 디테일 조회 및 조회 카운트 입니다.")
    @PostMapping(value = "/goods-detail")
    public ResponseEntity<?> getGoodsDetail(@RequestBody ShoppingGoodsDetailReqDto shoppingGoodsDetailReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(shoppingService.getGoodsDetail(serviceUser,shoppingGoodsDetailReqDto));
    }

    @Operation(summary = "장바구니", description = "쇼핑 장바구니 입니다.")
    @PostMapping(value = "/goods-keep")
    public ResponseEntity<?> userCard(@RequestBody ShoppingGoodsKeepReqDto shoppingGoodsKeepReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        shoppingService.userCart(shoppingGoodsKeepReqDto, serviceUser);
        return ObjectResult.ok();
    }
}
