package kr.co.dto.app.shopping.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShoppingGoodsDetailResDto {

    private String goodsId;
    private String goodsName;
    private String fileUrl;
    private String goodsFrom;
    private String goodsReleaseDate;
    private String goodsDeliveryDate;
    private String goodsPrice;
    private String eventPercent;
    private String eventPrice;
    private String discountPercent;
    private String discountPrice;
}
