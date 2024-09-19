package kr.co.mapper.app;

import kr.co.dto.app.home.response.CollectBadgeDto;
import kr.co.dto.app.home.response.CollectBadgeResDto;
import kr.co.dto.app.home.response.OpenGoodsResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {

    int selectUserBadgeByUserIdForCnt(String userId);

    List<OpenGoodsResDto> selectGoodsListForOpenYn(String userId, String language);

    List<CollectBadgeDto> selectUserBadgeByUserIdForCollect(String userId);
}
