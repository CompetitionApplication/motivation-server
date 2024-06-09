package kr.co.app.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.app.user.service.AppUserService;
import kr.co.common.ObjectResult;
import kr.co.dto.app.user.request.AppUserLoginReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "농장주인")
@Slf4j
@RestController
@RequestMapping("/api/v1/app/user")
@RequiredArgsConstructor
public class AppUserController {

    final AppUserService appUserService;

    @Operation(summary = "로그인", description = "농장주인 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> AppUserLogin(@RequestBody @Valid AppUserLoginReqDto appUserLoginReqDto) throws Exception {
        return ObjectResult.build(appUserService.appUserLogin(appUserLoginReqDto));
    }

    @Operation(summary = "토큰 재발급", description = "리프레시토큰ID를 통해 토큰을 재발급 합니다.")
    @GetMapping("/refresh-token")
    public ResponseEntity<?> AppUserRefreshToken(@Parameter(description = "리프레시토큰ID", example = "29af2259-2663-11ef-81e3-02001701d75b") @RequestParam(required = true) String refreshTokenId) throws Exception {
        return ObjectResult.build(appUserService.appUserRefreshToken(refreshTokenId));
    }
}
