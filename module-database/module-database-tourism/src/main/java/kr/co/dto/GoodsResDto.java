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
    @Schema(description = "발송지", example = "어디")
    private String goodsFrom;
    @Schema(description = "상품 출시일", example = "2021-01-01")
    private String goodsReleaseDate;
    @Schema(description = "예상 배송일", example = "2021-01-01")
    private String goodsDeliveryDate;
    @Schema(description = "뱃지 해금 개수", example = "1")
    private Integer badgeOpenCount;
    private String areaCode;
    private String detailAreaCode;
}
