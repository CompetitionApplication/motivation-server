package kr.co.app.alarm.service;

import kr.co.dto.app.alarm.request.AlarmReadReqDto;
import kr.co.dto.app.alarm.response.AlarmCountResDto;
import kr.co.dto.app.alarm.response.AlarmResDto;
import kr.co.entity.User;
import kr.co.mapper.app.AlarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    final AlarmMapper alarmMapper;

    @Override
    public List<AlarmResDto> alarms(User user){
        List<AlarmResDto> alarmResDtoList = alarmMapper.selectAlarm(user);
        return alarmResDtoList;
    }

    @Override
    public AlarmCountResDto alarmCount(User user){
        int cnt = alarmMapper.selectAlarmCount(user);
        AlarmCountResDto r = new AlarmCountResDto();
        r.setAlarmCnt(cnt);
        return r;
    }

    @Override
    public void alarmRead(User user, AlarmReadReqDto alarmReadReqDto) {
        alarmMapper.updateAlarm(user, alarmReadReqDto);
    }
}
