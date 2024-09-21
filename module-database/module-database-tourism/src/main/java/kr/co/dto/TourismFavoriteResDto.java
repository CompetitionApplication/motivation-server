package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourismFavoriteResDto {
    private String tourismId;
    private String tourismName;
    private String imageUrl;
}
