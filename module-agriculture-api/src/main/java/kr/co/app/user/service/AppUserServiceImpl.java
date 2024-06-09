package kr.co.app.user.service;

import kr.co.auth.JwtUtil;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.user.request.AppLoginReqDto;
import kr.co.dto.app.user.response.AppLoginResDto;
import kr.co.entity.Farm;
import kr.co.entity.Refreshtoken;
import kr.co.mapper.app.AppUserMapper;
import kr.co.mapper.web.CommonMapper;
import kr.co.mapper.web.FarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    final AppUserMapper appUserMapper;
    final FarmMapper farmMapper;
    final JwtUtil jwtUtil;
    final CommonMapper commonMapper;

    @Override
    public AppLoginResDto appUserLogin(AppLoginReqDto appLoginReqDto){
        AppLoginResDto r = new AppLoginResDto();

        Farm farm = farmMapper.selectFarmByFarmAppIdAndFarmAppPw(appLoginReqDto.getFarmAppId(), appLoginReqDto.getFarmAppPw());

        if(farm == null) {
            throw new CommonException(CommonErrorCode.NOT_FOUND_LOGIN_ID.getCode(), CommonErrorCode.NOT_FOUND_LOGIN_ID.getMessage());
        }

        String accessToken = jwtUtil.generateToken(appLoginReqDto.getFarmAppId());
        String refreshToken = jwtUtil.generateRefreshToken(appLoginReqDto.getFarmAppId());

        Refreshtoken refreshtoken = appUserMapper.selectRefreshtokenByFarmId(farm.getFarm_id());

        String refreshTokenId = "";
        if(refreshtoken == null){
            refreshTokenId = commonMapper.selectUUID();
            appUserMapper.insertRefrshtoken(farm.getFarm_id(), refreshToken, refreshTokenId);
        }else{
            refreshTokenId = refreshtoken.getRefreshtoken_id();
            appUserMapper.updateRefrshtoken(farm.getFarm_id(), refreshToken);
        }

        r.setAccessToken(accessToken);
        r.setRefreshTokenId(refreshTokenId);

        return r;
    }

    @Override
    public AppLoginResDto appUserRefreshToken(String refreshTokenId){
        AppLoginResDto r = new AppLoginResDto();

        Refreshtoken refreshtoken = appUserMapper.selectRefreshtokenByRefrshTokenId(refreshTokenId);

        if(refreshtoken != null){
            Farm farm = farmMapper.selectFarmByFarmIdForFarm(refreshtoken.getUser_id());
            String accessToken = jwtUtil.generateToken(farm.getFarm_id());
            String refreshToken = jwtUtil.generateRefreshToken(farm.getFarm_id());

            appUserMapper.updateRefrshtoken(farm.getFarm_id(), refreshToken);

            r.setAccessToken(accessToken);
            r.setRefreshTokenId(refreshTokenId);
        }

        return r;
    }
}
