package kr.co.dto.web.farm.response;

import lombok.Data;

@Data
public class FarmDetailResDto {

    private String farmId;
    private String farmKindNm;
    private String farmName;
    private String farmZip;
    private String farmUseAmt;
    private String reviewStar;
    private String reviewStarCnt;
    private String farmLongitude;
    private String farmLatitude;
    private String farmUseTimeDetail;

}
