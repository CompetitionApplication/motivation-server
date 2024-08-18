package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodsResDto {
    private String goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsColor;
    private String goodsSize;
}
