package kr.co.dto.app.shopping.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ShoppingMainReqDto {

    @NotBlank
    @Pattern(regexp = "^(KOR|ENG)$")
    private String language;
}
