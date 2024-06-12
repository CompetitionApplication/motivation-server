package kr.co.app.myPage.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.myPage.request.MyPageInfoSetReqDto;
import kr.co.dto.app.myPage.response.MyPageResDto;
import kr.co.dto.web.farm.response.FarmBannerResDto;
import kr.co.entity.Farm;
import kr.co.entity.FileGroup;
import kr.co.entity.User;
import kr.co.mapper.app.MyPageMapper;
import kr.co.mapper.web.FarmMapper;
import kr.co.mapper.web.FileMapper;
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
    final FileMapper fileMapper;

    @Override
    public MyPageResDto info(User user){
        MyPageResDto r = myPageMapper.info(user);
        List<FarmBannerResDto> bannerImage = farmMapper.selectFarmBannerImage(user.getUser_id());
        r.setFarmBannerImageList(bannerImage);
        return r;
    }

    @Override
    public void infoSet(MyPageInfoSetReqDto myPageInfoSetReqDto){
        //농장 확인
        Farm farm = farmMapper.selectFarmByFarmIdForFarm(myPageInfoSetReqDto.getFarmId());
        if(farm == null){
            throw new CommonException(CommonErrorCode.FARM_NOT_FOUND.getCode(),CommonErrorCode.FARM_NOT_FOUND.getMessage());
        }

        //메인이미지 확인
        FileGroup farmMainImage = fileMapper.selectFileGroup(myPageInfoSetReqDto.getFarmMainImageId());
        if(farmMainImage == null){
            throw new CommonException(CommonErrorCode.NOT_FOUND_FILE_GROUP_ID.getCode(),CommonErrorCode.NOT_FOUND_FILE_GROUP_ID.getMessage());
        }

        //배너이미지 확인
        FileGroup farmBannerImage = fileMapper.selectFileGroup(myPageInfoSetReqDto.getFarmBannerImageId());
        if(farmBannerImage == null){
            throw new CommonException(CommonErrorCode.NOT_FOUND_FILE_GROUP_ID.getCode(),CommonErrorCode.NOT_FOUND_FILE_GROUP_ID.getMessage());
        }

        //시작시간 종료시간 확인
        if(!farmMapper.checkFarmUseTimeA(myPageInfoSetReqDto)){
            throw new CommonException(CommonErrorCode.CHECK_FARM_USE_TIME_START_END.getCode(),CommonErrorCode.CHECK_FARM_USE_TIME_START_END.getMessage());
        }

        //체험시간이 시작시간 종료시간에 나눠 떨어지는지 확인
        if(!farmMapper.checkFarmUseTimeB(myPageInfoSetReqDto)){
            throw new CommonException(CommonErrorCode.CHECK_FARM_USE_TIME.getCode(),CommonErrorCode.CHECK_FARM_USE_TIME.getMessage());
        }

        //종료
        farmMapper.updateFarm(myPageInfoSetReqDto);
    }

}
