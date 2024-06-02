package kr.co.dto.web.farm.response;

import lombok.Data;

@Data
public class FarmsResDto {

    private String farmKind;
    private String farmName;
    private String farmZip;
    private String farmMaxUserCnt;
    private String farmUseAmt;
    private String fileUrl;
}
