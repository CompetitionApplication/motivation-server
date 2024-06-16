package kr.co.app.alarm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.app.alarm.service.AlarmService;
import kr.co.common.ListResult;
import kr.co.common.ObjectResult;
import kr.co.dto.app.alarm.request.AlarmReadReqDto;
import kr.co.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "alarm", description = "알람")
@Slf4j
@RestController
@RequestMapping("/api/v1/app/alarm")
@RequiredArgsConstructor
public class AlarmController {

    final AlarmService alarmService;

    @Operation(summary = "알람 여부 조회", description = "읽지 않은 알람이 있는지 확인합니다.")
    @GetMapping("/alarm-count")
    public ResponseEntity<?> alarmCount(@AuthenticationPrincipal User user) throws Exception {
        return ObjectResult.build(alarmService.alarmCount(user));
    }

    @Operation(summary = "알람조회", description = "알람을 조회 합니다.")
    @GetMapping("/alarm")
    public ResponseEntity<?> alarms(@AuthenticationPrincipal User user) throws Exception {
        return ListResult.build(alarmService.alarms(user));  
    }

    @Operation(summary = "알람 읽음", description = "알람 읽음 입니다.")
    @PutMapping("/alarm-read")
    public ResponseEntity<?> alarmRead(@AuthenticationPrincipal User user, @Valid @RequestBody AlarmReadReqDto alarmReadReqDto) throws Exception {
        alarmService.alarmRead(user,alarmReadReqDto);
        return ObjectResult.ok();
    }
}
