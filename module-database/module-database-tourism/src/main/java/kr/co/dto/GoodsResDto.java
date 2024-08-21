package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodsResDto {
    @Schema(description = "굿즈ID", example = "1")
    private String goodsId;
    @Schema(description = "굿즈명", example = "티셔츠")
    private String goodsName;
    @Schema(description = "굿즈금액", example = "10000원")
    private String goodsPrice;
    @Schema(description = "굿즈색상", example = "빨강")
    private String goodsColor;
    @Schema(description = "굿즈사이즈", example = "L")
    private String goodsSize;
}
