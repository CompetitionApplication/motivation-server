package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.AdminLoginReqDto;
import kr.co.dto.AdminLoginUserDto;
import kr.co.service.admin.AdminLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "어드민 로그인", description = "어드민 로그인합니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/admin/login")
@Slf4j
public class AdminLoginController {

    private final AdminLoginService adminLoginService;

    @PostMapping("")
    public ResponseEntity<AdminLoginUserDto> adminLogin(@RequestBody AdminLoginReqDto adminLoginReqDto) {
        return ResponseEntity.ok(adminLoginService.login(adminLoginReqDto));
    }
}
