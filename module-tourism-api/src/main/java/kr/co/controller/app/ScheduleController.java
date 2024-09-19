package kr.co.controller.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.common.ObjectResult;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.schedule.request.ScheduleDetailReqDto;
import kr.co.dto.app.schedule.request.ScheduleFavoriteReqDto;
import kr.co.dto.app.schedule.request.ScheduleRegReqDto;
import kr.co.service.app.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "schedule", description = "일정 ")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/app/schedule")
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Operation(summary = "리스트", description = "지도에 표시될 일정 리스트 입니다.")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getSchedules(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(scheduleService.getSchedules(serviceUser));
    }

    @Operation(summary = "리스트 상세 조회", description = "지도에 표시된 일정 디테일 입니다.")
    @GetMapping(value = "/list-detail")
    public ResponseEntity<?> getScheduleDetail(@RequestBody ScheduleDetailReqDto scheduleDetailReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(scheduleService.getScheduleDetail(scheduleDetailReqDto, serviceUser));
    }

    @Operation(summary = "즐겨찾기", description = "일정 즐겨찾기 입니다.")
    @PostMapping(value = "/favorite")
    public ResponseEntity<?> scheduleFavorite(@RequestBody ScheduleFavoriteReqDto scheduleFavoriteReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        scheduleService.scheduleFavorite(scheduleFavoriteReqDto, serviceUser);
        return ObjectResult.ok();
    }

    @Operation(summary = "등록", description = "일정 등록 입니다.")
    @PostMapping(value = "/reg")
    public ResponseEntity<?> scheduleReg(@RequestBody ScheduleRegReqDto scheduleRegReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        scheduleService.scheduleReg(scheduleRegReqDto, serviceUser);
        return ObjectResult.ok();
    }

}
