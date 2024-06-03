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

        User user = userMapper.selectUserByEmail(loginReqDto.getUserEmail());

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

        String refreshTokenId = "";
        if(refreshtoken == null){
            refreshTokenId = commonMapper.selectUUID();
            userMapper.insertRefrshtoken(userId, refreshToken, refreshTokenId);
        }else{
            refreshTokenId = refreshtoken.getRefreshtoken_id();
            userMapper.updateRefrshtoken(userId, refreshToken);
        }

        r.setAccessToken(accessToken);
        r.setRefreshTokenId(refreshTokenId);

        return r;
    }

    @Override
    public LoginResDto refreshToken(String refreshTokenId){
        LoginResDto r = new LoginResDto();

        Refreshtoken refreshtoken = userMapper.selectRefreshtokenByRefrshTokenId(refreshTokenId);

        if(refreshtoken != null){
            User user = userMapper.selectUserByUserId(refreshtoken.getUser_id());
            String accessToken = jwtUtil.generateToken(user.getUser_email());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUser_email());

            userMapper.updateRefrshtoken(user.getUser_id(), refreshToken);

            r.setAccessToken(accessToken);
            r.setRefreshTokenId(refreshTokenId);
        }

        return r;
    }
}
