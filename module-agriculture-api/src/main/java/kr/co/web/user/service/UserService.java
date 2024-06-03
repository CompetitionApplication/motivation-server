package kr.co.web.user.service;

import kr.co.dto.web.farm.request.LoginReqDto;
import kr.co.dto.web.farm.response.LoginResDto;

public interface UserService {

    LoginResDto login(LoginReqDto loginReqDto);
}
