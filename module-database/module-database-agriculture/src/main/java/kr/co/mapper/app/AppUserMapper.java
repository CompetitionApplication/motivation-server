package kr.co.mapper.app;

import kr.co.entity.Refreshtoken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppUserMapper {
    Refreshtoken selectRefreshtokenByFarmId(String farmId);
    void insertRefrshtoken(String farmId, String refreshToken, String refreshTokenId);
    void updateRefrshtoken(String farmId, String refreshToken);
    Refreshtoken selectRefreshtokenByRefrshTokenId(String refreshTokenId);
}
