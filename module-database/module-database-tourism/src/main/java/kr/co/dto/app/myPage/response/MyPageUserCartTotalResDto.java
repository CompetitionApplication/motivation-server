package kr.co.dto.app.myPage.response;

import lombok.Data;

import java.util.List;

@Data
public class MyPageUserCartTotalResDto {

    private String totalGoodsPrice;
    private String totalDiscount;
    private String totalDiscountPrice;
    private String totalBadgeDiscount;
    private String totalBadgeDiscountPrice;
    private String totalPrice;
    private List<MyPageUserCartResDto> cartList;
}
