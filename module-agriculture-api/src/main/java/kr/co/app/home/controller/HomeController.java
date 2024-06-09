package kr.co.app.home.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.app.home.service.HomeService;
import kr.co.app.user.service.AppUserService;
import kr.co.common.ListResult;
import kr.co.common.ObjectResult;
import kr.co.dto.app.home.request.StatusChangeReqDto;
import kr.co.dto.app.user.request.AppUserLoginReqDto;
import kr.co.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "home", description = "홈")
@Slf4j
@RestController
@RequestMapping("/api/v1/app/home")
@RequiredArgsConstructor
public class HomeController {

    final HomeService homeService;

    @Operation(summary = "홈 조회", description = "홈 메인 리스트를 조회 합니다.<br><br>" +
            "[param info]<br>" +
            "* homeTab(홈탭)<br>" +
            "00 : 예약도착<br>" +
            "01 : 예약확정<br>" +
            "02 : 예약취소<br>" +
            "외 : 전체<br>")
    @GetMapping("/list")
    public ResponseEntity<?> homeList(@Parameter(description = "홈탭", example = "03") @RequestParam(required = true) String homeTab){
        return ListResult.build(homeService.homtList(homeTab));
    }

    @Operation(summary = "예약 상태 변경", description = "예약 상태를 변경합니다.<br><br>" +
                                                      "[param info]<br>" +
                                                      "* reservationStatus(예약상)<br>" +
                                                      "01 : 예약확정<br>" +
                                                      "02 : 예약취소<br>")
    @PostMapping("/status-change")
    public ResponseEntity<?> statusChange(@Valid @RequestBody StatusChangeReqDto statusChangeReqDto, @AuthenticationPrincipal User user){
        homeService.statusChange(statusChangeReqDto, user);
        return ObjectResult.ok();
    }

}
