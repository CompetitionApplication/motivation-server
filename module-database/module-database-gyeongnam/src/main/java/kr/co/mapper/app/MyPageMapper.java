package kr.co.mapper.app;

import kr.co.dto.app.myPage.response.MyPageResDto;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {
    MyPageResDto info(User user);
}
