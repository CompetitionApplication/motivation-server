package kr.co.dto;

import lombok.Data;

@Data
public class GiveLocalItemReqDto {
    private String giveLocalItemName;
    private String giveLocalItemPrice;
    private String specialBadgeCodeName;
}
