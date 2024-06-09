package kr.co.dto.app.myPage.response;

import kr.co.dto.web.farm.response.FarmBannerResDto;
import lombok.Data;

import java.util.List;

@Data
public class MyPageResDto {

    private String farmId;
    private String farmMainImageUrl;
    private String farmBannerImageId;
    private String farmName;
    private String farmIntrcn;
    private String farmKind;
    private String farmKindNm;
    private String farmZip;
    private String farmOwnerTel;
    private String farmOwnerName;
    private String farmAccountNo;
    private String farmUseDay;
    private String farmUseTime;
    private String farmUseStartTime;
    private String farmUseEndTime;
    private String farmUseTimeDetail;
    private String farmUseAmt;
    private List<FarmBannerResDto> FarmBannerImageList;

}
