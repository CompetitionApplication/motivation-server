package kr.co.auth;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.common.UsernameNotFoundException;
import kr.co.repository.AdminUserRepository;
import kr.co.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AdminUserRepository adminUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        if (userId.contains("admin")) {
            AdminLoginUser adminLoginUser = adminUserRepository.findByAdminUserEmail(userId).map(AdminLoginUser::new)
                    .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));
        log.info("adminLoginUser : {}", adminLoginUser);
            return new TourismAdminUser(adminLoginUser);
        } else {
            LoginUser loginUser = userRepository.findByUserEmailAndDelYnFalse(userId).map(LoginUser::new)
                    .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));
//test
            return new TourismUser(loginUser);
        }
    }
}
