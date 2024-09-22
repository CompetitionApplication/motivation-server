package kr.co.dto.app.shopping.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShoppingGoodsBuyReqDto {

    @NotBlank
    private String goodsId;

    @NotBlank
    @Schema(example = "1")
    private String orderCount;

    @NotBlank
    @Schema(example = "1000")
    private String orderPrice;
}
