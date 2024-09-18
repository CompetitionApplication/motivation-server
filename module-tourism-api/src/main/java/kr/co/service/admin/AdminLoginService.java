package kr.co.service.admin;

import kr.co.auth.JwtUtil;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.AdminLoginReqDto;
import kr.co.dto.AdminLoginUserDto;
import kr.co.entity.AdminUser;
import kr.co.entity.RefreshToken;
import kr.co.repository.AdminUserRepository;
import kr.co.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final AdminUserRepository adminUserRepository;
    private final JwtUtil jwtUtil;

    public AdminLoginUserDto login(AdminLoginReqDto adminLoginReqDto) {
        AdminUser adminUser = adminUserRepository.findByAdminUserEmailAndAdminUserPassword(adminLoginReqDto.getAdminUserEmail(), adminLoginReqDto.getPassword())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        if(adminUser != null) {
            //:::엑세스 토큰 발급 , 리프레시 토큰 발급:::
            String accessToken = jwtUtil.generateToken(adminUser.getAdminUserEmail());

            return new AdminLoginUserDto(accessToken);
        }
        return  null;
    }
}
