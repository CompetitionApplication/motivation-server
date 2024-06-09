package kr.co.app.myPage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.app.myPage.service.MyPageService;
import kr.co.common.ObjectResult;
import kr.co.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "myPage", description = "마이페이지")
@Slf4j
@RestController
@RequestMapping("/api/v1/app/myPage")
@RequiredArgsConstructor
public class MyPageController {

    final MyPageService myPageService;

    @Operation(summary = "마이페이지", description = "마이페이지를 조회 합니다.")
    @GetMapping("/info")
    public ResponseEntity<?> info(@AuthenticationPrincipal User user){
        return ObjectResult.build(myPageService.info(user));
    }


}
