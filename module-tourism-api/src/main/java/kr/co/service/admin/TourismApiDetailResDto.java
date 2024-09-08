package kr.co.service.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.entity.TourismApi;
import lombok.Data;

import java.util.List;

@Data
public class TourismApiDetailResDto {
    @Schema(description = "관광지명", example = "경복궁")
    private String tourismName;
    @Schema(description = "관광지주소", example = "남현동")
    private String tourismAddress;
    @Schema(description = "관광지링크", example = "https://www.royalpalace.go.kr/main")
    private String tourismLink;
    @Schema(description = "관광지연락처", example = "02-3700-3900")
    private String tourismContact;
    @Schema(description = "지역코드", example = "1")
    private String areaCode;
    @Schema(description = "상세지역코드", example = "1")
    private String detailAreaCode;
    @Schema(description = "관광지맵X", example = "126.9769")
    private String tourismMapX;
    @Schema(description = "관광지맵Y", example = "37.5769")
    private String tourismMapY;
    @Schema(description = "뱃지코드", example = "1")
    private String badgeCode;

    private List<String> tourismImages;

    public TourismApiDetailResDto(TourismApi tourismApi, List<String> tourismImages){
        this.tourismName = tourismApi.getTitle();
        this.tourismAddress = tourismApi.getAddr1();
        this.tourismLink = tourismApi.getTourismLink();
        this.tourismContact = tourismApi.getTel();
        this.areaCode = tourismApi.getAreacode();
        this.detailAreaCode = tourismApi.getDetailAreaCode();
        this.tourismMapX = tourismApi.getMapx();
        this.tourismMapY = tourismApi.getMapy();
        this.badgeCode = tourismApi.getBadgeCode().getBadgeCodeType();
        this.tourismImages = tourismImages;
    }
}
