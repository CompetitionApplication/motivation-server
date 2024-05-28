package kr.co.service;

import kr.co.auth.JwtUtil;
import kr.co.common.AES256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {


/*    @Transactional
    public LoginResDto login(LoginReqDto loginReqDto) throws Exception {

        //:::기존에 정보가 있는 유저인지 확인:::
        boolean userInfoCheck = userRepository.existsByUserEmail(AES256Util.encrypt(loginReqDto.getUserEmail()));

        if (userInfoCheck) {
            //:::기존 회원:::
            User userInfo = userRepository.findByUserEmail(AES256Util.encrypt(loginReqDto.getUserEmail()))
                    .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));

            //:::엑세스 토큰 발급 , 리프레시 토큰 발급:::
            String accessToken = jwtUtil.generateToken(userInfo.getUserEmail());
            String refreshToken = jwtUtil.generateRefreshToken(userInfo.getUserEmail());

            //::: 리프레시 토큰 업데이트 :::
            RefreshToken refreshTokenInfo = refreshTokenRepository.findByUser(userInfo)
                    .orElseThrow(() -> new CommonException(CommonErrorCode.COMMON_FAIL.getCode(), CommonErrorCode.COMMON_FAIL.getMessage()));

            refreshTokenInfo.updateRefreshToken(refreshToken);
            //::: 결과값 반환 :::
            return new LoginResDto(accessToken,refreshTokenInfo.getRefreshtokenId());

        } else {
            //:::신규 회원:::

            //:::정보 DB저장:::
            User savedUserInfo = userRepository.save(new User(loginReqDto));

            //:::엑세스 토큰 발급 , 리프레시 토큰 발급:::
            String accessToken = jwtUtil.generateToken(savedUserInfo.getUserEmail());
            String refreshToken = jwtUtil.generateRefreshToken(savedUserInfo.getUserEmail());

            //::: 리프레시 토큰 DB저장 :::
            RefreshToken refreshTokenInfo = refreshTokenRepository.save(new RefreshToken(refreshToken, savedUserInfo));

            //::: 결과값 반환 :::
            return new LoginResDto(accessToken,refreshTokenInfo.getRefreshtokenId());
        }


    }*/
}
