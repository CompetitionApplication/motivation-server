package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderStatusResDto {
    private String orderStatus;
    private String orderStatusName;
}
