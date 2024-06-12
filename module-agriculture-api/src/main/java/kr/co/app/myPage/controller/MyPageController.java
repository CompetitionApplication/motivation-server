package kr.co.app.myPage.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.app.myPage.service.MyPageService;
import kr.co.common.ObjectResult;
import kr.co.dto.app.myPage.request.MyPageInfoSetReqDto;
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

    @Operation(summary = "마이페이지 수정", description = "마이페이지를 수정 합니다.<br><br>" +
                                                       "[param info]<br>" +
                                                       "* farmKind(농장종류)<br>" +
                                                       "01 : 농장<br>" +
                                                       "02 : 목장<br>" +
                                                       "03 : 체험<br>" +
                                                       "* farmUseTimeDetail(체험시간)<br>" +
                                                       "0.5 : 30분<br>" +
                                                       "1.0 : 1시간<br>" +
                                                       "1.5 : 1시간 30분<br>" +
                                                       ",,,")
    @PostMapping("/info-set")
    public ResponseEntity<?> infoSet(@Valid @RequestBody MyPageInfoSetReqDto myPageInfoSetReqDto){
        myPageService.infoSet(myPageInfoSetReqDto);
        return ObjectResult.ok();
    }


}
