package kr.co.app.user.service;

import kr.co.dto.app.user.request.AppUserLoginReqDto;
import kr.co.dto.app.user.response.AppUserLoginResDto;

public interface AppUserService {

    AppUserLoginResDto appUserLogin(AppUserLoginReqDto appUserLoginReqDto) throws Exception;

    AppUserLoginResDto appUserRefreshToken(String refreshTokenId);
}
