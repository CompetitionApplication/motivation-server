package kr.co.dto.app.alarm.response;

import lombok.Data;

@Data
public class AlarmResDto {
    private String alarmId;

    private String alarmTitle;

    private String alarmContent;

    private String alarmDate;

    private String alarmReadYn;
}
