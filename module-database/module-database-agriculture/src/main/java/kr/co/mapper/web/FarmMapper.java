package kr.co.mapper.web;

import kr.co.dto.web.farm.response.FarmBannerResDto;
import kr.co.dto.web.farm.response.FarmDetailResDto;
import kr.co.dto.web.farm.response.FarmsResDto;
import kr.co.dto.web.openApiClient.response.FarmClientResDto;
import kr.co.entity.Farm;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FarmMapper {

    List<FarmsResDto> selectFarms(@Param("farmKind") String farmKind, @Param("farmName") String farmName, @Param("farmUseDay") String farmUseDay, @Param("farmMaxUserCnt") String farmMaxUserCnt);

    FarmDetailResDto selectFarmByFarmId(@Param("farmId") String farmId);
    int selectFarm(@Param("farmName") String farmName);
    void insertFarm(@Param("row") FarmClientResDto.Row row, @Param("id") String id, @Param("pw") String pw);
    List<FarmBannerResDto> selectFarmBannerImage(@Param("farmId") String farmId);
    Farm selectFarmByFarmIdForFarm(String farmId);
    Farm selectFarmByFarmAppIdAndFarmAppPw(String farmAppId, String farmAppPw);
    User selectFarmByFarmIdForUser(String farmId);
}
