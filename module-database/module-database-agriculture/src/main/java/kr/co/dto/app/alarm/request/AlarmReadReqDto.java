package kr.co.dto.app.alarm.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlarmReadReqDto {

    @NotNull(message = "알람ID는 필수값입니다.")
    @Schema(description = "알람ID", example = "9a8668c7-28cc-11ef-83a0-02001701d75b")
    private String alarmId;
}
