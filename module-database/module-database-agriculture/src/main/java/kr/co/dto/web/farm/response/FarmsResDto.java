package kr.co.dto.web.farm.response;

import lombok.Data;

@Data
public class FarmsResDto {

    private String farmId;
    private String farmKindNm;
    private String farmName;
    private String farmZip;
    private String farmUseAmt;
    private String farmMainImageUrl;
    private String reviewStar;
    private String reviewStarCnt;

}
