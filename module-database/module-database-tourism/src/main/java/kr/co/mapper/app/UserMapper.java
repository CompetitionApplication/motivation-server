package kr.co.mapper.app;

import kr.co.dto.GoodsBuyResDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.myPage.response.MyPageScheduleResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    ServiceUser selectUserByUserEmail(String userEamil);

    List<GoodsBuyResDto> selectOrderItemByUserId(String userId);

    List<MyPageScheduleResDto> selectTourismScheduleByUserId(String userId);
}
