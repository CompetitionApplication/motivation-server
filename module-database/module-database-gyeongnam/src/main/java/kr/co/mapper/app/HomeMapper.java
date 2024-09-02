package kr.co.mapper.app;

import kr.co.dto.app.home.request.StatusChangeReqDto;
import kr.co.dto.app.home.response.HomeResDto;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeMapper {

    List<HomeResDto> homeList(@Param("homeTab") String homeTab, @Param("user") User user);
    void statusChange(@Param("statusChangeReqDto") StatusChangeReqDto statusChangeReqDto, @Param("user") User user);
}
