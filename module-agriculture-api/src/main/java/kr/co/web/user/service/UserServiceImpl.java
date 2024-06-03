package kr.co.web.user.service;

import kr.co.auth.JwtUtil;
import kr.co.dto.web.farm.request.LoginReqDto;
import kr.co.dto.web.farm.response.LoginResDto;
import kr.co.entity.Refreshtoken;
import kr.co.entity.User;
import kr.co.mapper.CommonMapper;
import kr.co.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    final UserMapper userMapper;

    final CommonMapper commonMapper;

    final JwtUtil jwtUtil;

    @Override
    public LoginResDto login(LoginReqDto loginReqDto){
        LoginResDto r = new LoginResDto();

        User user = userMapper.selectUser(loginReqDto);

        String userId = "";
        if(user == null) {
            userId = commonMapper.selectUUID();
            userMapper.insertUser(loginReqDto, userId);
        }else{
            userId = user.getUser_id();
        }


        String accessToken = jwtUtil.generateToken(loginReqDto.getUserEmail());
        String refreshToken = jwtUtil.generateRefreshToken(loginReqDto.getUserEmail());

        Refreshtoken refreshtoken = userMapper.selectRefreshtokenByUserId(userId);

        if(refreshtoken == null){
            userMapper.insertRefrshtoken(userId, refreshToken);
        }else{
            userMapper.updateRefrshtoken(userId, refreshToken);
        }

        r.setAccessToken(accessToken);
        r.setRefreshTokenId(refreshToken);

        return r;
    }
}
