package kr.co.dto.app.schedule.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ScheduleRegReqDto {

    @NotBlank
    private String tourismApiId;

    @NotBlank
    @Schema(description = "일정 시간", example = "2024-09-28 15:03")
    private String scheduledDate;

    @NotBlank
    @Schema(description = "등록 여부", example = "N")
    @Pattern(regexp = "^(Y|N)$")
    private String delYn;
}
