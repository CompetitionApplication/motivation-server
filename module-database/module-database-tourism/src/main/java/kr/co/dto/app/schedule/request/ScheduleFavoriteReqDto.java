package kr.co.dto.app.schedule.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ScheduleFavoriteReqDto {

    @NotBlank
    private String tourismApiId;

    @NotBlank
    @Pattern(regexp = "^(Y|N)$")
    private String favoriteYn;
}
