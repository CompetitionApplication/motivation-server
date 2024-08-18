package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResDto {
    private String orderId;
    private String orderDatetime;
    private String orderUser;
    private String orderPrice;
    private String orderStatus;
}
