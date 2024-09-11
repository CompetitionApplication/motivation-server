package kr.co.dto;

import lombok.Data;

@Data
public class OrderStatusUpdateReqDto {
    private String orderItemId;
    private String orderStatus;
}
