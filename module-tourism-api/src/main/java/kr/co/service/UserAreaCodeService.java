package kr.co.service;

import kr.co.auth.AdminLoginUser;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.UserAreaCodeResDto;
import kr.co.entity.AdminUser;
import kr.co.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAreaCodeService {

    private final AdminUserRepository adminUserRepository;


    @Transactional(readOnly = true)
    public UserAreaCodeResDto getUserAreaCodeInfo(AdminLoginUser adminLoginUser) {
        AdminUser adminUserInfo = adminUserRepository.findByAdminUserEmail(adminLoginUser.getAdminUser().getAdminUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        String areaCodeName = adminUserInfo.getDetailAreaCode().getAreaCode().getName();
        String areaDetailCodeName = adminUserInfo.getDetailAreaCode().getName();


        String userAreaCodeName = areaCodeName + " " + areaDetailCodeName;

        return UserAreaCodeResDto.builder()
                .userAreaName(userAreaCodeName)
                .build();
    }
}
