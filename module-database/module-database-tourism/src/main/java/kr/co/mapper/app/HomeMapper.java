package kr.co.mapper.app;

import kr.co.dto.app.common.ServiceUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeMapper {

    int selectUserBadgeByUserId(String userId);
}
