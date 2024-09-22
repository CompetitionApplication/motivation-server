package kr.co.controller.app;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.common.ListResult;
import kr.co.common.ObjectResult;
import kr.co.dto.app.alarm.request.AlarmReadReqDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.service.app.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "alarm", description = "알림")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/app/alarm")
@Slf4j
public class AlarmController {

    private final AlarmService alarmService;

    @Operation(summary = "리스트", description = "알림 리스트 입니다.")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAlarms(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ListResult.build(alarmService.getAlarms(serviceUser));
    }

    @Operation(summary = "읽음", description = "알림 읽음 입니다.")
    @PostMapping(value = "/read")
    public ResponseEntity<?> alarmRead(@RequestBody AlarmReadReqDto alarmReadReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        alarmService.alarmRead(alarmReadReqDto, serviceUser);
        return ObjectResult.ok();
    }

    @Operation(summary = "새 알림 여부", description = "안읽은 알림 또는 새로운 알림이 있는지 확인 합니다.")
    @GetMapping(value = "/new-chk")
    public ResponseEntity<?> alarmNewChk(@AuthenticationPrincipal ServiceUser serviceUser) {
        return ObjectResult.build(alarmService.alarmNewChk(serviceUser));
    }
}
