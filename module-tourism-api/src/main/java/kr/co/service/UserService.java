package kr.co.service;


import kr.co.auth.JwtUtil;
import kr.co.common.AES256Cipher;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.*;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.myPage.response.MyPageScheduleResDto;
import kr.co.dto.app.myPage.response.MyPageUserCartResDto;
import kr.co.dto.app.myPage.response.MyPageUserCartTotalResDto;
import kr.co.entity.*;
import kr.co.mapper.app.UserMapper;
import kr.co.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final GoodsBuyRepository goodsBuyRepository;
    private final TourismApiRepository tourismApiRepository;
    private final TourismFavoriteRepository tourismFavoriteRepository;

    private final UserMapper userMapper;


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

            if (refreshTokenInfo == null) {
                refreshTokenInfo = refreshTokenRepository.save(new RefreshToken(refreshToken, userInfo));
            } else {
                refreshTokenInfo.updateRefreshToken(refreshToken);
            }
            //::: 결과값 반환 :::
            return new LoginResDto(accessToken, refreshTokenInfo.getRefreshTokenId());
        }
        return null;
    }

    @Transactional
    public void join(SignUpReqDto signUpReqDto) throws Exception {

        //:::이미 가입된 유저인지 확인:::
        userRepository.findByUserEmail(signUpReqDto.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.ALREADY_EXIST_USER.getCode(), CommonErrorCode.ALREADY_EXIST_USER.getMessage()));

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

    @Transactional(readOnly = true)
    public List<GoodsBuyResDto> goods(ServiceUser serviceUser) {
        /*
        User userInfo = userRepository.findByUserEmail(serviceUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));
        List<GoodsBuy> goodsBuys = goodsBuyRepository.findByUser(userInfo);

        return goodsBuys.stream().map(goodsBuy -> GoodsBuyResDto.builder()
                .goodsName(goodsBuy.getGoods().getGoodsName())
                .goodsBuyDate(goodsBuy.getRegDatetime())
                .goodsPrice(goodsBuy.getGoods().getGoodsPrice())
                .build()).collect(Collectors.toList());
        */
        List<GoodsBuyResDto> r = userMapper.selectOrderItemByUserId(serviceUser.getUserId());

        return r;
    }

    @Transactional(readOnly = true)
    public List<UserBadgeResDto> badges(ServiceUser serviceUser) {
        User userInfo = userRepository.findByUserEmail(serviceUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));
        List<UserBadge> userBadges = userBadgeRepository.findByUser(userInfo);

        return userBadges.stream().map(userBadge -> UserBadgeResDto.builder()
                .tourismName(userBadge.getTourismApi().getTitle())
                .badgeGetDatetime(userBadge.getRegDatetime())
                .badgeName(userBadge.getTourismApi().getBadgeCode().getBadgeCodeType())
                .build()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TourismFavoriteResDto> favorites(ServiceUser serviceUser) {
        User userInfo = userRepository.findByUserEmail(serviceUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));
        return tourismFavoriteRepository.findAllByUser(userInfo).stream().map(tourismFavorite -> TourismFavoriteResDto.builder()
                .tourismId(tourismFavorite.getTourismApi().getTourismApiId())
                .tourismName(tourismFavorite.getTourismApi().getTitle())
                .imageUrl(tourismFavorite.getTourismApi().getFirstimage())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public void withdraw(ServiceUser serviceUser) {
        User userInfo = userRepository.findByUserEmail(serviceUser.getUserEmail())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_USER.getCode(), CommonErrorCode.NOT_FOUND_USER.getMessage()));
        userInfo.withdrawUser();
    }

    public List<MyPageScheduleResDto> getSchedule(ServiceUser serviceUser){
        List<MyPageScheduleResDto> r = userMapper.selectTourismScheduleByUserId(serviceUser.getUserId());
        return r;
    }

    public MyPageUserCartTotalResDto getCart(ServiceUser serviceUser){
        MyPageUserCartTotalResDto r = new MyPageUserCartTotalResDto();

        List<MyPageUserCartResDto> cartList = userMapper.selectUserCartByUserId(serviceUser.getUserId());
        r.setCartList(cartList);

        if(cartList.size() > 0){
            int sum = cartList.stream()
                    .mapToInt(obj -> Integer.parseInt(obj.getGoodsPrice()))
                    .sum();
            r.setTotalGoodsPrice(Integer.toString(sum));

            r.setTotalPrice(Integer.toString(sum));
        }else{
            r.setTotalGoodsPrice("0");
            r.setTotalPrice("0");
        }

        r.setTotalDiscount("0%");
        r.setTotalDiscountPrice("0");
        r.setTotalBadgeDiscount("0%");
        r.setTotalBadgeDiscountPrice("0");
        return r;
    }
}
