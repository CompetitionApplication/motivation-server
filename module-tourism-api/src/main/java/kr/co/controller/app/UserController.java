package kr.co.controller.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.common.ListResult;
import kr.co.common.ObjectResult;
import kr.co.dto.LoginReqDto;
import kr.co.dto.SignUpReqDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "login", description = "로그인/회원가입")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/app/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원탈퇴", description = "회원탈퇴를 합니다.")
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@AuthenticationPrincipal ServiceUser serviceUser) {
        userService.withdraw(serviceUser);
        return ObjectResult.ok();
    }

    @Operation(summary = "로그인", description = "로그인을 합니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto,@AuthenticationPrincipal ServiceUser serviceUser) throws Exception {

        return ObjectResult.build(userService.login(loginReqDto));
    }
    @Operation(summary = "회원가입", description = "회원가입을 합니다.")
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid SignUpReqDto signUpReqDto) throws Exception {
        userService.join(signUpReqDto);
        return ObjectResult.ok();
    }

    @Operation(summary = "마이페이지", description = "마이페이지를 보여줍니다.")
    @GetMapping("/mypage")
    public ResponseEntity<?> myPage(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(userService.myPage(serviceUser));
    }

    @Operation(summary = "굿즈 구매 배송내역", description = "굿즈 구매 배송내역을 보여줍니다.")
    @GetMapping("/mypage/goods")
    public ResponseEntity<?> goods(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ListResult.build(userService.goods(serviceUser));
    }

    @Operation(summary = "뱃지 획독 내역", description = "뱃지 획독 내역을 조회합니다.")
    @GetMapping("/mypage/badges")
    public ResponseEntity<?> badges(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ListResult.build(userService.badges(serviceUser));
    }

    @Operation(summary = "즐겨찾기 목록 조회", description = "즐겨찾기 목록 조회")
    @GetMapping("/mypage/favorite")
    public ResponseEntity<?> favorites(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ListResult.build(userService.favorites(serviceUser));
    }

    @Operation(summary = "일정 목록 조회", description = "일정 목록 조회")
    @GetMapping("/mypage/schedule")
    public ResponseEntity<?> getSchedule(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ListResult.build(userService.getSchedule(serviceUser));
    }

    @Operation(summary = "장바구니 목록 조회", description = "일정 목록 조회")
    @GetMapping("/mypage/cart")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(userService.getCart(serviceUser));
    }



}
