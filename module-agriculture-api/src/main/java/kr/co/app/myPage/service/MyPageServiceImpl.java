package kr.co.app.myPage.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.myPage.request.MyPageInfoSetReqDto;
import kr.co.dto.app.myPage.response.MyPageFarmBannerResDto;
import kr.co.dto.app.myPage.response.MyPageResDto;
import kr.co.dto.web.farm.response.FarmBannerResDto;
import kr.co.entity.Farm;
import kr.co.entity.File;
import kr.co.entity.FileGroup;
import kr.co.entity.User;
import kr.co.mapper.app.MyPageMapper;
import kr.co.mapper.web.CommonMapper;
import kr.co.mapper.web.FarmMapper;
import kr.co.mapper.web.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    final MyPageMapper myPageMapper;
    final FarmMapper farmMapper;
    final FileMapper fileMapper;
    final CommonMapper commonMapper;

    @Override
    public MyPageResDto info(User user){
        MyPageResDto r = myPageMapper.info(user);
        List<MyPageFarmBannerResDto> bannerImage = farmMapper.selectFarmBannerImageForMyPageFarmBannerResDto(user.getUser_id());
        r.setFarmBannerImageList(bannerImage);
        return r;
    }

    @Override
    @Transactional
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
        String fileGroupId = commonMapper.selectUUID();
        for(MyPageInfoSetReqDto.file file: myPageInfoSetReqDto.getFarmBannerImageList()){
            //파일그룹테이블 삭제
            File fileChk = fileMapper.selectFile(file.getBannerImageFileId());
            if(fileChk == null){
                throw new CommonException(CommonErrorCode.NOT_FOUND_FILE_ID.getCode(),CommonErrorCode.NOT_FOUND_FILE_ID.getMessage());
            }
            fileMapper.deleteFileGroup(fileChk.getFile_group_id());

            //파일테이블 파일그룹ID업데이트
            fileMapper.updateFile(file.getBannerImageFileId(),fileGroupId);
        }
        //파일그룹테이블 신규채번ID 인서트
        fileMapper.insertFileGroup(fileGroupId);

        //시작시간 종료시간 확인  
        if(!farmMapper.checkFarmUseTimeA(myPageInfoSetReqDto)){
            throw new CommonException(CommonErrorCode.CHECK_FARM_USE_TIME_START_END.getCode(),CommonErrorCode.CHECK_FARM_USE_TIME_START_END.getMessage());
        }

        //체험시간이 시작시간 종료시간에 나눠 떨어지는지 확인
        if(!farmMapper.checkFarmUseTimeB(myPageInfoSetReqDto)){
            throw new CommonException(CommonErrorCode.CHECK_FARM_USE_TIME.getCode(),CommonErrorCode.CHECK_FARM_USE_TIME.getMessage());
        }

        //업데이트
        farmMapper.updateFarm(myPageInfoSetReqDto,fileGroupId);
    }

}
