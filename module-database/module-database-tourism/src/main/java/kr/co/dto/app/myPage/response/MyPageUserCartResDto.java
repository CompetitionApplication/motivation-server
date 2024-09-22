package kr.co.dto.app.myPage.response;

import lombok.Data;

@Data
public class MyPageUserCartResDto {

    private String goodsId;
    private String fileUrl;
    private String goodsName;
    private String goodsPrice;
    private String orderCount;
}
