package kr.co.dto.app.shopping.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ShoppingGoodsKeepReqDto {

    @NotBlank
    private String goodsId;

    @NotBlank
    @Schema(description = "등록/해제", example = "N")
    @Pattern(regexp = "^(Y|N)$")
    private String delYn;
}
