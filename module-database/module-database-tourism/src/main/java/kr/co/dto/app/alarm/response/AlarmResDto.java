package kr.co.dto.app.alarm.response;

import lombok.Data;

@Data
public class AlarmResDto {

    private String pushId;
    private String pushTitle;
    private String pushContent;
    private String regDatetime;
    private String readYn;
}
