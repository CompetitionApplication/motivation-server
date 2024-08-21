package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GoodsUploadReqDto {
    @Schema(description = "굿즈명", example = "티셔츠")
    private String goodsName;
    @Schema(description = "굿즈금액", example = "10000")
    private String goodsPrice;
    @Schema(description = "굿즈색상", example = "빨강")
    private String goodsColor;
    @Schema(description = "굿즈사이즈", example = "L")
    private String goodsSize;

    public GoodsUploadReqDto(String goodsName, String goodsPrice, String goodsColor, String goodsSize) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsColor = goodsColor;
        this.goodsSize = goodsSize;
    }
}
