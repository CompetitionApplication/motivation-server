package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
@Builder
public class TourPlaceResDto {
    @Schema(description = "관광지 ID", example = "1")
    private String tourPlaceId;
    @Schema(description = "관광지명", example = "경복궁")
    private String tourPlaceName;
    @Schema(description = "관광지주소", example = "남현동")
    private String tourPlaceAddress;
    @Schema(description = "관광지링크", example = "https://www.royalpalace.go.kr/main")
    private String tourPlaceLink;
    @Schema(description = "관광지연락처", example = "02-3700-3900")
    private String tourPlaceContact;
}
