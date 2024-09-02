package kr.co.mapper.app;

import kr.co.dto.app.alarm.request.AlarmReadReqDto;
import kr.co.dto.app.alarm.response.AlarmResDto;
import kr.co.entity.Alarm;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlarmMapper {
    List<AlarmResDto> selectAlarm(User user);
    int selectAlarmCount(User user);
    void updateAlarm(@Param("user") User user, @Param("alarmReadReqDto") AlarmReadReqDto alarmReadReqDto);
    void insertAlarm(Alarm alarm);
}
