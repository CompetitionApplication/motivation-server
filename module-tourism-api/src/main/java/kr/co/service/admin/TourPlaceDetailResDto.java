package kr.co.service.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.entity.TourPlace;
import lombok.Data;

@Data
public class TourPlaceDetailResDto {
    @Schema(description = "여행지명", example = "경복궁")
    private String tourPlaceName;
    @Schema(description = "여행지주소", example = "서울특별시 종로구 사직로 161")
    private String tourPlaceAddress;
    @Schema(description = "여행지링크", example = "https://www.royalpalace.go.kr/")
    private String tourPlaceLink;
    @Schema(description = "여행지연락처", example = "02-3700-3900")
    private String tourPlaceContact;

    public TourPlaceDetailResDto(TourPlace tourPlace) {
        this.tourPlaceName = tourPlace.getTourPlaceName();
        this.tourPlaceAddress = tourPlace.getTourPlaceAddress();
        this.tourPlaceLink = tourPlace.getTourPlaceLink();
        this.tourPlaceContact = tourPlace.getTourPlaceContact();
    }
}
