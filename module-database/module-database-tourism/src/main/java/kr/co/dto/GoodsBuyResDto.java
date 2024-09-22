package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodsBuyResDto {
    private String orderItemId;
    private String goodsId;
    private String goodsName;
    private String goodsPrice;
    private String orderStatus;
    private String goodsBuyDate;
}
