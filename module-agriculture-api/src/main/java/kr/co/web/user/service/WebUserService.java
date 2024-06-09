package kr.co.web.user.service;

import kr.co.dto.web.user.request.WebUserAccountReqDto;
import kr.co.dto.web.user.request.WebUserLoginReqDto;
import kr.co.dto.web.user.response.WebUserInfoResDto;
import kr.co.dto.web.user.response.WebUserLoginResDto;
import kr.co.dto.web.notice.response.NoticeResDto;
import kr.co.entity.User;

import java.util.List;

public interface WebUserService {

    WebUserLoginResDto webUserLogin(WebUserLoginReqDto loginReqDto) throws Exception;
    void webUserAccount(WebUserAccountReqDto webUserAccountReqDto) throws Exception;
    WebUserLoginResDto webUserRefreshToken(String refreshTokenId);
    void drop(User user);
    List<NoticeResDto> notice();
    WebUserInfoResDto myInfo(User user);
}
