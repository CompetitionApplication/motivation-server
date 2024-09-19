package kr.co.dto.app.alarm.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlarmReadReqDto {

    @NotBlank
    private String alarmId;
}
