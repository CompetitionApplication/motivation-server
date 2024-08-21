package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPlaceUploadReqDto {
    @Schema(description = "관광지명", example = "경복궁")
    private String tourPlaceName;
    @Schema(description = "관광지주소", example = "남현동")
    private String tourPlaceAddress;
    @Schema(description = "관광지링크", example = "https://www.royalpalace.go.kr/main")
    private String tourPlaceLink;
    @Schema(description = "관광지연락처", example = "02-3700-3900")
    private String tourPlaceContact;

    public TourPlaceUploadReqDto(String tourPlaceName, String tourPlaceAddress, String tourPlaceLink, String tourPlaceContact) {
        this.tourPlaceName = tourPlaceName;
        this.tourPlaceAddress = tourPlaceAddress;
        this.tourPlaceLink = tourPlaceLink;
        this.tourPlaceContact = tourPlaceContact;
    }
}
