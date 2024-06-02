package kr.co.mapper;

import kr.co.dto.web.farm.response.FarmsResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmMapper {

    List<FarmsResDto> farms();
}
