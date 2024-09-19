package kr.co.controller.app;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kr.co.common.ObjectResult;
import kr.co.dto.LoginReqDto;
import kr.co.dto.SignUpReqDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


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




}
