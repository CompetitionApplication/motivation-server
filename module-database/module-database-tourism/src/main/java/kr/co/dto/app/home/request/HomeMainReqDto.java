package kr.co.dto.app.home.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HomeMainReqDto {

    @NotBlank
    @Pattern(regexp = "^(KOR|ENG)$")
    private String language;
}
