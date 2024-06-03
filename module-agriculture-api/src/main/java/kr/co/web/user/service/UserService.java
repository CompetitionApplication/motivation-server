package kr.co.web.user.service;

import kr.co.dto.web.farm.request.LoginReqDto;
import kr.co.dto.web.farm.response.LoginResDto;
import kr.co.entity.User;

public interface UserService {

    LoginResDto login(LoginReqDto loginReqDto);
    LoginResDto refreshToken(String refreshTokenId);
    void drop(User user);
}
