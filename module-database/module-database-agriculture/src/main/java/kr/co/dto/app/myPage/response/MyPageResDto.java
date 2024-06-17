package kr.co.dto.app.myPage.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({
        "farmId",
        "farmName",
        "farmKind",
        "farmKindNm",
        "farmIntrcn",
        "farmZip",
        "farmOwnerTel",
        "farmOwnerName",
        "farmAccountNo",
        "farmUseDay",
        "farmUseTime",
        "farmUseStartTime",
        "farmUseEndTime",
        "farmUseTimeDetail",
        "farmUseAmt",
        "farmMainImageId",
        "farmMainImageUrl",
        "farmBannerImageId",
        "farmBannerImageList"
})
public class MyPageResDto {

    private String farmId;
    private String farmName;
    private String farmKind;
    private String farmKindNm;
    private String farmIntrcn;
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
    private String farmMainImageId;
    private String farmMainImageUrl;
    private String farmBannerImageId;
    private List<MyPageFarmBannerResDto> farmBannerImageList;

}
