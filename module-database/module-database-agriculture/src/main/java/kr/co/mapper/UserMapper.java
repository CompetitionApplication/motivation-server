package kr.co.mapper;

import kr.co.dto.web.farm.request.LoginReqDto;
import kr.co.entity.Refreshtoken;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.nio.file.LinkOption;

@Mapper
public interface UserMapper {

    User selectUser(LoginReqDto loginReqDto);
    void insertUser(@Param("loginReqDto") LoginReqDto loginReqDto, @Param("userId") String userId);
    Refreshtoken selectRefreshtokenByUserId(@Param("userId") String userId);
    void insertRefrshtoken(@Param("userId") String userId, @Param("refreshToken") String refreshToken);
    void updateRefrshtoken(@Param("userId") String userId, @Param("refreshToken") String refreshToken);

}
