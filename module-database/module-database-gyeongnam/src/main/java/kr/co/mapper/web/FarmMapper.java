package kr.co.mapper.web;

import kr.co.dto.app.myPage.request.MyPageInfoSetReqDto;
import kr.co.dto.app.myPage.response.MyPageFarmBannerResDto;
import kr.co.dto.web.farm.response.FarmBannerResDto;
import kr.co.dto.web.farm.response.FarmDetailResDto;
import kr.co.dto.web.farm.response.FarmUseTimeDetailResDto;
import kr.co.dto.web.farm.response.FarmsResDto;
import kr.co.dto.web.openApiClient.response.FarmClientResDto;
import kr.co.entity.Farm;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FarmMapper {

    List<FarmsResDto> selectFarms(String farmKind, String farmName, String farmUseDay, String farmMaxUserCnt, String orderByKind);

    FarmDetailResDto selectFarmByFarmId(String farmId);
    int selectFarm(String farmName);
    void insertFarm(@Param("row") FarmClientResDto.Row row, @Param("id") String id, @Param("pw") String pw);
    List<FarmBannerResDto> selectFarmBannerImage(String farmId);
    Farm selectFarmByFarmIdForFarm(String farmId);
    Farm selectFarmByFarmAppIdAndFarmAppPw(String farmAppId, String farmAppPw);
    User selectFarmByFarmIdForUser(String farmId);
    List<FarmUseTimeDetailResDto> selectFarmUseTimeDetailList(String farmId);
    void updateFarm(@Param("myPageInfoSetReqDto") MyPageInfoSetReqDto myPageInfoSetReqDto, @Param("fileGroupId") String fileGroupId);
    boolean checkFarmUseTimeA(MyPageInfoSetReqDto myPageInfoSetReqDto);
    boolean checkFarmUseTimeB(MyPageInfoSetReqDto myPageInfoSetReqDto);
    List<MyPageFarmBannerResDto> selectFarmBannerImageForMyPageFarmBannerResDto(String farmId);
}
