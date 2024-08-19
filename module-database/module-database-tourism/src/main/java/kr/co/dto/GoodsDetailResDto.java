package kr.co.dto;

import kr.co.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailResDto {
    private String goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsColor;
    private String goodsSize;

    public GoodsDetailResDto(Goods goods) {
        this.goodsName = goods.getGoodsName();
        this.goodsPrice = goods.getGoodsPrice();
        this.goodsColor = goods.getGoodsColor();
        this.goodsSize = goods.getGoodsSize();
    }

}
