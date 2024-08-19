package kr.co.service.admin;

import kr.co.entity.TourPlace;
import lombok.Data;

@Data
public class TourPlaceDetailResDto {
    private String tourPlaceName;
    private String tourPlaceAddress;
    private String tourPlaceLink;
    private String tourPlaceContact;

    public TourPlaceDetailResDto(TourPlace tourPlace) {
        this.tourPlaceName = tourPlace.getTourPlaceName();
        this.tourPlaceAddress = tourPlace.getTourPlaceAddress();
        this.tourPlaceLink = tourPlace.getTourPlaceLink();
        this.tourPlaceContact = tourPlace.getTourPlaceContact();
    }
}
