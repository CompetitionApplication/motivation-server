package kr.co.web.user.service;

import kr.co.auth.JwtUtil;
import kr.co.common.AES256Util;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.web.user.request.WebUserAccountReqDto;
import kr.co.dto.web.user.request.WebUserLoginReqDto;
import kr.co.dto.web.user.response.WebUserInfoResDto;
import kr.co.dto.web.user.response.WebUserLoginResDto;
import kr.co.dto.web.notice.response.NoticeResDto;
import kr.co.entity.Refreshtoken;
import kr.co.entity.User;
import kr.co.mapper.web.CommonMapper;
import kr.co.mapper.web.WebUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebUserServiceImpl implements WebUserService {

    @Value("${aes.key}")
    private String aesKEY;

    @Value("${aes.iv}")
    private String aesIV;

    final WebUserMapper webUserMapper;

    final CommonMapper commonMapper;

    final JwtUtil jwtUtil;

    @Override
    public WebUserLoginResDto webUserLogin(WebUserLoginReqDto webUserLoginReqDto) throws Exception{
        WebUserLoginResDto r = new WebUserLoginResDto();

        User login = webUserMapper.webUserLogin(webUserLoginReqDto.getUserWebId(), AES256Util.AES256encrypt(webUserLoginReqDto.getUserWebPw(),aesKEY,aesIV));

        if(login == null) {
            throw new CommonException(CommonErrorCode.NOT_FOUND_LOGIN_ID.getCode(), CommonErrorCode.NOT_FOUND_LOGIN_ID.getMessage());
        }

        String userId = login.getUser_id();

        String accessToken = jwtUtil.generateToken(login.getUser_id());
        String refreshToken = jwtUtil.generateRefreshToken(login.getUser_id());

        Refreshtoken refreshtoken = webUserMapper.selectRefreshtokenByUserId(userId);

        String refreshTokenId = "";
        if(refreshtoken == null){
            refreshTokenId = commonMapper.selectUUID();
            webUserMapper.insertRefrshtoken(userId, refreshToken, refreshTokenId);
        }else{
            refreshTokenId = refreshtoken.getRefreshtoken_id();
            webUserMapper.updateRefrshtoken(userId, refreshToken);
        }

        r.setAccessToken(accessToken);
        r.setRefreshTokenId(refreshTokenId);

        return r;
    }

    @Override
    public void webUserAccount(WebUserAccountReqDto webUserAccountReqDto) throws Exception{
        User login = webUserMapper.webUserByUserWebId(webUserAccountReqDto.getUserWebId());

        if(login != null) {
            throw new CommonException(CommonErrorCode.DUPLICATION_ACCOUNT_ID.getCode(), CommonErrorCode.DUPLICATION_ACCOUNT_ID.getMessage());
        }

        webUserAccountReqDto.setUserWebPw(AES256Util.AES256encrypt(webUserAccountReqDto.getUserWebPw(),aesKEY,aesIV));

        webUserMapper.insertUser(webUserAccountReqDto);
    }

    @Override
    public WebUserLoginResDto webUserRefreshToken(String refreshTokenId){
        WebUserLoginResDto r = new WebUserLoginResDto();

        Refreshtoken refreshtoken = webUserMapper.selectRefreshtokenByRefrshTokenId(refreshTokenId);

        if(refreshtoken != null){
            User user = webUserMapper.selectUserByUserId(refreshtoken.getUser_id());
            String accessToken = jwtUtil.generateToken(user.getUser_email());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUser_email());

            webUserMapper.updateRefrshtoken(user.getUser_id(), refreshToken);

            r.setAccessToken(accessToken);
            r.setRefreshTokenId(refreshTokenId);
        }else{
            throw new CommonException(CommonErrorCode.NOT_FOUND_REFRESHTOKEN_ID.getCode(), CommonErrorCode.NOT_FOUND_REFRESHTOKEN_ID.getMessage());
        }

        return r;
    }

    @Override
    public void drop(User user){
        webUserMapper.updateDropUser(user);
    }

    @Override
    public List<NoticeResDto> notice(){
        List<NoticeResDto> r = webUserMapper.selectNotice();
        return r;
    }

    @Override
    public WebUserInfoResDto myInfo(User user){
        WebUserInfoResDto r = webUserMapper.myInfo(user);
        return r;
    }
}
