package kr.co.controller;

import kr.co.auth.JwtUtil;
import kr.co.auth.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {
    private final JwtUtil jwtUtil;
    private final UserDetailService userDetailService;

    @GetMapping("/api/v1/test")
    public String get(){
        //UserDetails loginUser = userDetailService.loadUserByUsername("tests");
        return jwtUtil.generateToken("test2");
    }
    @GetMapping("/test")
    public void test(){
        log.info("good");
    }
}
