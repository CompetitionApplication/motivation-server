package kr.co.mapper.app;

import kr.co.dto.app.home.response.HomeResDto;
import kr.co.entity.Refreshtoken;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

    List<HomeResDto> homeList(String homeTab);
}
