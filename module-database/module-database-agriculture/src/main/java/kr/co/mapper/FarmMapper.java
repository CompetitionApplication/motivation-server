package kr.co.mapper;

import kr.co.dto.web.farm.response.FarmsResDto;
import kr.co.dto.web.openApiClient.response.FarmClientResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FarmMapper {

    List<FarmsResDto> selectFarms(@Param("farmKind") String farmKind, @Param("farmName") String farmName, @Param("farmUseDay") String farmUseDay, @Param("farmMaxUserCnt") String farmMaxUserCnt);

    int selectFarm(@Param("farmName") String farmName);
    void insertFarm(@Param("row") FarmClientResDto.Row row, @Param("id") String id, @Param("pw") String pw);
}
