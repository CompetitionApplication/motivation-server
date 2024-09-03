package kr.co.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.co.auth.TourismUser;
import kr.co.common.ObjectResult;
import kr.co.dto.LoginReqDto;
import kr.co.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;


    @Operation(summary = "로그인", description = "로그인을 합니다.")
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto,@AuthenticationPrincipal TourismUser tourismUser) throws Exception {

        return ObjectResult.build(loginService.login(loginReqDto));
    }
}
