package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourismUploadReqDto {
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

    public TourismUploadReqDto(String tourismName, String tourismAddress, String tourismLink, String tourismContact, String areaCode, String detailAreaCode) {
        this.tourismName = tourismName;
        this.tourismAddress = tourismAddress;
        this.tourismLink = tourismLink;
        this.tourismContact = tourismContact;
        this.areaCode = areaCode;
        this.detailAreaCode = detailAreaCode;
    }
}
