package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoodsBuyResDto {
    private String goodsName;
    private String goodsPrice;
    private String goodsBuyDate;
}
