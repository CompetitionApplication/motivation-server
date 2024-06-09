package kr.co.app.myPage.service;

import kr.co.dto.app.myPage.response.MyPageResDto;
import kr.co.dto.web.farm.response.FarmBannerResDto;
import kr.co.entity.User;
import kr.co.mapper.app.MyPageMapper;
import kr.co.mapper.web.FarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    final MyPageMapper myPageMapper;
    final FarmMapper farmMapper;

    @Override
    public MyPageResDto info(User user){
        MyPageResDto r = myPageMapper.info(user);
        List<FarmBannerResDto> bannerImage = farmMapper.selectFarmBannerImage(user.getUser_id());
        r.setFarmBannerImageList(bannerImage);
        return r;
    }

}
