package kr.co.controller;

import kr.co.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;

/*
    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto) throws Exception {
        return ObjectResult.build(loginService.login(loginReqDto));
    }*/
}
