package kr.co.app.home.service;

import kr.co.app.user.service.AppUserService;
import kr.co.auth.JwtUtil;
import kr.co.common.AES256Util;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.home.response.HomeResDto;
import kr.co.dto.app.user.request.AppUserLoginReqDto;
import kr.co.dto.app.user.response.AppUserLoginResDto;
import kr.co.entity.Farm;
import kr.co.entity.Refreshtoken;
import kr.co.mapper.app.AppUserMapper;
import kr.co.mapper.app.HomeMapper;
import kr.co.mapper.web.CommonMapper;
import kr.co.mapper.web.FarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    final HomeMapper homeMapper;

    @Override
    public List<HomeResDto> homtList(String homeTab){
        List<HomeResDto> r = homeMapper.homeList(homeTab);
        return r;
    }


}
