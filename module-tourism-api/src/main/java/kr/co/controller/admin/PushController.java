package kr.co.controller.admin;

import com.google.firebase.messaging.FirebaseMessagingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.PushDetailResDto;
import kr.co.dto.PushReqDto;
import kr.co.dto.PushResDto;
import kr.co.service.PushService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "push", description = "푸시 관련 서비스")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/push")
public class PushController {

    private final PushService pushService;

    @Operation(summary = "푸시 전송", description = "푸시 전송 서비스 입니다.")
    @PostMapping("")
    public ResponseEntity<?> sendPush(@RequestBody PushReqDto pushReqDto) throws FirebaseMessagingException {
        pushService.sendPush(pushReqDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "푸시 목록 조회", description = "푸시 목록 조회 서비스")
    @GetMapping("")
    public ResponseEntity<List<PushResDto>> getPushList() {
        return ResponseEntity.ok(pushService.getPushList());
    }

    @Operation(summary = "푸시 상세 조회", description = "푸시 상세 조회 서비스")
    @GetMapping("/{pushId}")
    public ResponseEntity<PushDetailResDto> getPushDetail(@PathVariable(value = "pushId") String pushId) {
        return ResponseEntity.ok(pushService.getPushDetail(pushId));
    }

}
