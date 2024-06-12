package kr.co.app.alarm.service;

import kr.co.dto.app.alarm.request.AlarmReadReqDto;
import kr.co.dto.app.alarm.response.AlarmCountResDto;
import kr.co.dto.app.alarm.response.AlarmResDto;
import kr.co.entity.User;

import java.util.List;

public interface AlarmService {

    List<AlarmResDto> alarms(User user);
    AlarmCountResDto alarmCount(User user);
    void alarmRead(User user, AlarmReadReqDto alarmReadReqDto);
}
