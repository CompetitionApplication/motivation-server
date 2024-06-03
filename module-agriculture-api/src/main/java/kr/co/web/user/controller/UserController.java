package kr.co.web.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.common.ObjectResult;
import kr.co.dto.web.farm.request.LoginReqDto;
import kr.co.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "사용자")
@Slf4j
@RestController
@RequestMapping("/api/v1/web/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto) throws Exception {
        return ObjectResult.build(userService.login(loginReqDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto) throws Exception {
        return ObjectResult.build(userService.login(loginReqDto));
    }
}
