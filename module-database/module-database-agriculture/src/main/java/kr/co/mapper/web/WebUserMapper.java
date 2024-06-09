package kr.co.mapper.web;

import kr.co.dto.web.user.request.WebUserAccountReqDto;
import kr.co.dto.web.user.request.WebUserLoginReqDto;
import kr.co.dto.web.notice.response.NoticeResDto;
import kr.co.dto.web.user.response.WebUserInfoResDto;
import kr.co.entity.Refreshtoken;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebUserMapper {

    User selectUserByEmail(String userEmail);
    void insertUser(WebUserAccountReqDto webUserAccountReqDto);
    Refreshtoken selectRefreshtokenByUserId(String userId);
    Refreshtoken selectRefreshtokenByRefrshTokenId(String refreshTokenId);
    void insertRefrshtoken(String userId, String refreshToken, String refreshTokenId);
    void updateRefrshtoken(String userId, String refreshToken);
    User selectUserByUserId(String userId);
    void updateDropUser(User user);
    List<NoticeResDto> selectNotice();
    User webUserLogin(String userWebId, String userWebPw);
    User webUserByUserWebId(String userWebId);
    WebUserInfoResDto myInfo(User user);

}
