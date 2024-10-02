package kr.co.controller.app;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.common.ObjectResult;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.qr.QrReqDto;
import kr.co.service.app.QrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "큐알관련 서비스", description = "큐알찍은 후 받는 서비스")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/app/qr")
@Slf4j
public class QrController {

    private final QrService qrService;

    @PostMapping("")
    public ResponseEntity<?> receiveQrData(@RequestBody QrReqDto qrReqDto, @AuthenticationPrincipal ServiceUser serviceUser) {
        qrService.receiveQrData(qrReqDto, serviceUser);
        // 20240928 sgpark 응답값 수정, 배포처리
        return ObjectResult.ok();
        //return ResponseEntity.ok().build();
    }
}
