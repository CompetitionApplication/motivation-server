package kr.co.dto.app.shopping.response;

import lombok.Data;

@Data
public class ShoppingGoodsDto {

    private String goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsOpenYn;
    private String fileUrl;
}
