package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiveLocalItemResDto {
    private String giveLocalItemId;
    private String giveLocalItemName;
    private String giveLocalItemPrice;
    private String badgeCodeName;
}
