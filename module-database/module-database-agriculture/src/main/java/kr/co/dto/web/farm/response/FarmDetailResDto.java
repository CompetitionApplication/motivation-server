package kr.co.dto.web.farm.response;

import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.List;

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
    private String farmUseTimeAndDetailTimeFormat;
    private List<FarmBannerResDto> bannerImageList;
    private String farmOwnerTel;
    private String farmIntrcn;
    private List<FarmReviewResDto> reviewList;
    private List<FarmUseTimeDetailResDto> farmUseTimeDetailList;
    private String farmEventDiscountRate;
    private String farmEventDiscountOriginalAmt;
    private String farmUseDay;

}
