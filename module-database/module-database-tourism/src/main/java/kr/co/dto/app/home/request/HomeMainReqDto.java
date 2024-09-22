package kr.co.dto.app.home.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HomeMainReqDto {

    @NotBlank
    @Pattern(regexp = "^(KOR|ENG)$")
    @Schema(description = "언어", example = "KOR")
    private String language;
}
