package kr.co.web.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.auth.AgricultureUser;
import kr.co.common.ObjectResult;
import kr.co.dto.web.farm.request.LoginReqDto;
import kr.co.entity.User;
import kr.co.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "사용자")
@Slf4j
@RestController
@RequestMapping("/api/v1/web/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @Operation(summary = "로그인", description = "사용자 소셜 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto) throws Exception {
        return ObjectResult.build(userService.login(loginReqDto));
    }

    @Operation(summary = "토큰 재발급", description = "리프레시토큰ID를 통해 토큰을 재발급 합니다.")
    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Parameter(description = "리프레시토큰ID", example = "07a76d41-21bc-11ef-81e3-02001701d75b") @RequestParam(required = true) String refreshTokenId) throws Exception {
        return ObjectResult.build(userService.refreshToken(refreshTokenId));
    }

    @Operation(summary = "회원 탈퇴", description = "회원정보를 탈퇴 합니다.")
    @PostMapping("/drop")
    public ResponseEntity<?> drop(@AuthenticationPrincipal User user) throws Exception {
        userService.drop(user);
        return ObjectResult.ok();
    }

    @Operation(summary = "공지사항 리스트 조회", description = "공지사항을 조회 합니다.(작업중)")
    @GetMapping("/notice")
    public ResponseEntity<?> notice(@AuthenticationPrincipal User user) throws Exception {
        return ObjectResult.ok();
    }
}
