package kr.co.dto.app.home.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OpenGoodsReqDto {

    @NotBlank
    @Pattern(regexp = "^(Y|N)$")
    private String openYn;
}
