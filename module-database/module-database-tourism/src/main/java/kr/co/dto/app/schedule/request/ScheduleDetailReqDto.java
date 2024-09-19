package kr.co.dto.app.schedule.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ScheduleDetailReqDto {

    @NotBlank
    private String tourismApiId;
}
