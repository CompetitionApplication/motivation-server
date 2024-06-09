package kr.co.app.user.service;

import kr.co.dto.app.user.request.AppLoginReqDto;
import kr.co.dto.app.user.response.AppLoginResDto;

public interface AppUserService {

    AppLoginResDto appUserLogin(AppLoginReqDto appLoginReqDto);

    AppLoginResDto appUserRefreshToken(String refreshTokenId);
}
