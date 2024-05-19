package kr.co.controller;

import jakarta.validation.Valid;
import kr.co.common.ObjectResult;
import kr.co.dto.LoginReqDto;
import kr.co.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto) throws Exception {
        return ObjectResult.build(loginService.login(loginReqDto));
    }
}
