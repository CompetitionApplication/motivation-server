package kr.co.mapper.app;

import kr.co.dto.app.alarm.response.AlarmNewChkResDto;
import kr.co.dto.app.alarm.response.AlarmResDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlarmMapper {

    List<AlarmResDto> selectPushByUserId(String userId);

    void insertPushRead(String userId, String alarmId);

    AlarmNewChkResDto selectPushForNew(String userId);

}
