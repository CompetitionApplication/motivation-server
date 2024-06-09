package kr.co.auth;

import kr.co.entity.User;
import kr.co.mapper.web.WebUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {

    final WebUserMapper webUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = webUserMapper.selectUserByEmail(userId);
        LoginUser loginUser = new LoginUser(user);

        return new AgricultureUser(loginUser);
    }
}
