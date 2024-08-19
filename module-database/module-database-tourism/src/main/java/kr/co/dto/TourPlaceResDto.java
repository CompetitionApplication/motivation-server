package kr.co.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Builder
public class TourPlaceResDto {
    private String tourPlaceId;
    private String tourPlaceName;
    private String tourPlaceAddress;
    private String tourPlaceLink;
    private String tourPlaceContact;
}
