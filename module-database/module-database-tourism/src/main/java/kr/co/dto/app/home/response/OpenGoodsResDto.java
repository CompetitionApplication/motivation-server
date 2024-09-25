package kr.co.dto.app.home.response;

import lombok.Data;

@Data
public class OpenGoodsResDto {

    private String goodsId;
    private String goodsName;
    private String goodsPrice;
    private String goodsOpenYn;
    private String fileUrl;
    private String badgeOpenCount;
}
