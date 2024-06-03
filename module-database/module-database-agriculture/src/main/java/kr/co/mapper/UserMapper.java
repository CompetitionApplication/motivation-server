package kr.co.mapper;

import kr.co.dto.web.farm.request.LoginReqDto;
import kr.co.entity.Refreshtoken;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.nio.file.LinkOption;

@Mapper
public interface UserMapper {

    User selectUserByEmail(@Param("userEmail") String userEmail);
    void insertUser(@Param("loginReqDto") LoginReqDto loginReqDto, @Param("userId") String userId);
    Refreshtoken selectRefreshtokenByUserId(@Param("userId") String userId);
    Refreshtoken selectRefreshtokenByRefrshTokenId(@Param("refreshTokenId") String refreshTokenId);
    void insertRefrshtoken(@Param("userId") String userId, @Param("refreshToken") String refreshToken, @Param("refreshTokenId") String refreshTokenId);
    void updateRefrshtoken(@Param("userId") String userId, @Param("refreshToken") String refreshToken);
    User selectUserByUserId(@Param("userId") String userId);
    void updateDropUser(User user);

}
