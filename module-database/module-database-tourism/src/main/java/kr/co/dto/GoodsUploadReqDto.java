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
    @Schema(description = "발송지", example = "서울")
    private String goodsFrom;
    @Schema(description = "출시일", example = "2021-01-01")
    private String goodsReleaseDate;
    @Schema(description = "예상 배송일", example = "2021-01-01")
    private String goodsDeliveryDate;

    public GoodsUploadReqDto(String goodsName, String goodsPrice, String goodsColor, String goodsSize, String goodsFrom, String goodsReleaseDate, String goodsDeliveryDate) {
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsColor = goodsColor;
        this.goodsSize = goodsSize;
        this.goodsFrom = goodsFrom;
        this.goodsReleaseDate = goodsReleaseDate;
        this.goodsDeliveryDate = goodsDeliveryDate;

    }
}
