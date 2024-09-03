package kr.co.dto;

import lombok.Data;

@Data
public class LocalItemUploadReqDto {

    private String localItemName;
    private String localItemPrice;
    private int localItemBadgeCount;
    private String areaCode;
    private String detailAreaCode;


    public LocalItemUploadReqDto(String localItemName, String localItemPrice, int localItemBadgeCount, String areaCode, String detailAreaCode) {
        this.localItemName = localItemName;
        this.localItemPrice = localItemPrice;
        this.localItemBadgeCount = localItemBadgeCount;
        this.areaCode = areaCode;
        this.detailAreaCode = detailAreaCode;
    }
}
