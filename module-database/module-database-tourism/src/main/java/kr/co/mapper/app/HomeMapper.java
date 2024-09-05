package kr.co.mapper.app;

import kr.co.dto.app.home.response.OpenGoodsResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

    int selectUserBadgeByUserId(String userId);

    List<OpenGoodsResDto> selectGoodsListForOpenYn(String userId);
}
