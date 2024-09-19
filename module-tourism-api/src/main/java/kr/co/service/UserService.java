package kr.co.service;


import kr.co.auth.JwtUtil;
import kr.co.common.AES256Cipher;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.LoginReqDto;
import kr.co.dto.LoginResDto;
import kr.co.dto.SignUpReqDto;
import kr.co.dto.UserMyPageResDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.entity.*;
import kr.co.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TripStyleRepository tripStyleRepository;
    private final HobbyRepository hobbyRepository;
    private final JwtUtil jwtUtil;
    private final UserBadgeRepository userBadgeRepository;


    @Transactional
    public LoginResDto login(LoginReqDto loginReqDto) throws Exception {

        //:::기존에 정보가 있는 유저인지 확인:::
        boolean userInfoCheck = userRepository.existsByUserEmail(AES256Cipher.encrypt(loginReqDto.getUserEmail()));

        if (userInfoCheck) {
            //:::기존 회원:::
            User userInfo = userRepository.findByUserEmail(AES256Cipher.encrypt(loginReqDto.getUserEmail()))
                    .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

            //:::엑세스 토큰 발급 , 리프레시 토큰 발급:::
            String accessToken = jwtUtil.generateToken(userInfo.getUserEmail());
            String refreshToken = jwtUtil.generateRefreshToken(userInfo.getUserEmail());



            //::: 리프레시 토큰 업데이트 :::
            RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(userInfo).orElse(null);

            if(refreshTokenInfo == null){
                refreshTokenInfo = refreshTokenRepository.save(new RefreshToken(refreshToken, userInfo));
            }else{
                refreshTokenInfo.updateRefreshToken(refreshToken);
            }
            //::: 결과값 반환 :::
            return new LoginResDto(accessToken, refreshTokenInfo.getRefreshTokenId());
        }
        return null;
    }

    @Transactional
    public void join(SignUpReqDto signUpReqDto) throws Exception {
        //:::유저정보저장:::
        User user = userRepository.save(new User(signUpReqDto));

        //:::취미저장:::
        signUpReqDto.getTripStyles().forEach(tripStyle -> {
            tripStyleRepository.save(new TripStyle(tripStyle, user));
        });
        //:::여행스타일저장:::
        signUpReqDto.getHobbyNames().forEach(hobbyName -> {
            hobbyRepository.save(new Hobby(hobbyName, user));
        });
    }

    @Transactional(readOnly = true)
    public UserMyPageResDto myPage(ServiceUser serviceUser) {
        //유저정보 조회
        User userInfo = userRepository.findByUserEmail(serviceUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

        //해당 계정의 얻은 뱃지개수 추출
        long userBadges = userBadgeRepository.countByUser(userInfo);

        return UserMyPageResDto.builder()
                .userName(userInfo.getUserName())
                .userGetBadgeCount(String.valueOf(userBadges))
                .build();
    }
}
