package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResDto {
    @Schema(description = "주문 ID", example = "1")
    private String orderId;
    @Schema(description = "굿즈 ID", example = "굿즈ID")
    private String goodsId;
    @Schema(description = "주문일시", example = "2021-08-01 12:00:00")
    private String orderDatetime;
    @Schema(description = "주문자", example = "홍길동")
    private String orderUser;
    @Schema(description = "주문가격", example = "10000")
    private String orderPrice;
    @Schema(description = "주문상태", example = "입금전")
    private String orderStatus;

}
