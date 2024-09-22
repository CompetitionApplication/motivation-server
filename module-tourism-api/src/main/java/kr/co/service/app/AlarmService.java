package kr.co.service.app;

import kr.co.dto.app.alarm.request.AlarmReadReqDto;
import kr.co.dto.app.alarm.response.AlarmNewChkResDto;
import kr.co.dto.app.alarm.response.AlarmResDto;
import kr.co.dto.app.common.ServiceUser;
import kr.co.mapper.app.AlarmMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {

    private final AlarmMapper alarmMapper;

    public List<AlarmResDto> getAlarms(ServiceUser serviceUser){

        //알림 리스트
        List<AlarmResDto> r = alarmMapper.selectPushByUserId(serviceUser.getUserId());
        if(r == null){
            r = new ArrayList<>();
        }

        return r;
    }

    public void alarmRead(AlarmReadReqDto alarmReadReqDto, ServiceUser serviceUser){
        alarmMapper.insertPushRead(serviceUser.getUserId(), alarmReadReqDto.getAlarmId());
    }

    public AlarmNewChkResDto alarmNewChk(ServiceUser serviceUser){

        //안읽은 알림, 새 알림 여부
        AlarmNewChkResDto r = alarmMapper.selectPushForNew(serviceUser.getUserId());

        return r;
    }

}
