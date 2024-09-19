package kr.co.dto.app.schedule.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ScheduleRegReqDto {

    @NotBlank
    private String tourismApiId;

    @NotBlank
    private String scheduledDate;
}
