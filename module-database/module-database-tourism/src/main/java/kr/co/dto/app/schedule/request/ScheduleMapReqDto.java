package kr.co.dto.app.schedule.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import kr.co.dto.app.schedule.response.ScheduleResDto;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleMapReqDto {

    @NotBlank
    @Pattern(regexp = "^(KOR|ENG)$")
    @Schema(description = "언어", example = "KOR")
    private String language;
}
