package kr.co.dto;

import lombok.Data;

@Data
public class LocalItemUploadReqDto {

    private String localItemName;
    private String localItemPrice;
    private String areaCode;
    private String detailAreaCode;


    public LocalItemUploadReqDto(String localItemName, String localItemPrice, String areaCode, String detailAreaCode) {
        this.localItemName = localItemName;
        this.localItemPrice = localItemPrice;
        this.areaCode = areaCode;
        this.detailAreaCode = detailAreaCode;
    }
}
