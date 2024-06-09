package kr.co.app.user.service;

import kr.co.auth.JwtUtil;
import kr.co.common.AES256Util;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.user.request.AppUserLoginReqDto;
import kr.co.dto.app.user.response.AppUserLoginResDto;
import kr.co.entity.Farm;
import kr.co.entity.Refreshtoken;
import kr.co.mapper.app.AppUserMapper;
import kr.co.mapper.web.CommonMapper;
import kr.co.mapper.web.FarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    @Value("${aes.key}")
    private String aesKEY;

    @Value("${aes.iv}")
    private String aesIV;

    final AppUserMapper appUserMapper;
    final FarmMapper farmMapper;
    final JwtUtil jwtUtil;
    final CommonMapper commonMapper;

    @Override
    public AppUserLoginResDto appUserLogin(AppUserLoginReqDto appLoginReqDto) throws Exception{
        AppUserLoginResDto r = new AppUserLoginResDto();

        Farm farm = farmMapper.selectFarmByFarmAppIdAndFarmAppPw(appLoginReqDto.getFarmAppId(), AES256Util.AES256encrypt(appLoginReqDto.getFarmAppPw(),aesKEY,aesIV));

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
    public AppUserLoginResDto appUserRefreshToken(String refreshTokenId){
        AppUserLoginResDto r = new AppUserLoginResDto();

        Refreshtoken refreshtoken = appUserMapper.selectRefreshtokenByRefrshTokenId(refreshTokenId);

        if(refreshtoken != null){
            Farm farm = farmMapper.selectFarmByFarmIdForFarm(refreshtoken.getUser_id());
            String accessToken = jwtUtil.generateToken(farm.getFarm_id());
            String refreshToken = jwtUtil.generateRefreshToken(farm.getFarm_id());

            appUserMapper.updateRefrshtoken(farm.getFarm_id(), refreshToken);

            r.setAccessToken(accessToken);
            r.setRefreshTokenId(refreshTokenId);
        }else{
            throw new CommonException(CommonErrorCode.NOT_FOUND_REFRESHTOKEN_ID.getCode(), CommonErrorCode.NOT_FOUND_REFRESHTOKEN_ID.getMessage());
        }

        return r;
    }
}
