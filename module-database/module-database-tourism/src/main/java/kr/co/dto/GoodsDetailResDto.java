package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailResDto {
    @Schema(description = "상품 ID", example = "1")
    private String goodsId;
    @Schema(description = "상품명", example = "티셔츠")
    private String goodsName;
    @Schema(description = "상품가격", example = "10000")
    private String goodsPrice;
    @Schema(description = "상품색상", example = "블랙")
    private String goodsColor;
    @Schema(description = "상품사이즈", example = "L")
    private String goodsSize;

    public GoodsDetailResDto(Goods goods) {
        this.goodsName = goods.getGoodsName();
        this.goodsPrice = goods.getGoodsPrice();
        this.goodsColor = goods.getGoodsColor();
        this.goodsSize = goods.getGoodsSize();
    }

}
