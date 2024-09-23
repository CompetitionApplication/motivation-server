package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.auth.AdminLoginUser;
import kr.co.dto.UserAreaCodeResDto;
import kr.co.dto.app.common.ServiceAdminUser;
import kr.co.service.UserAreaCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상단 지역명 조회", description = "상단 지역명 조회 서비시ㅡ")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user-area-code")
@Slf4j
public class UserAreaCodeController {

    private final UserAreaCodeService userAreaCodeService;

    @GetMapping("")
    public ResponseEntity<UserAreaCodeResDto> getUserAreaCodeInfo(@AuthenticationPrincipal ServiceAdminUser serviceAdminUser){
        return ResponseEntity.ok(userAreaCodeService.getUserAreaCodeInfo(serviceAdminUser));
    }
}
