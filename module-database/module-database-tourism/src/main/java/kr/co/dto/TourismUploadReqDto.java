package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.entity.AreaCode;
import kr.co.entity.DetailAreaCode;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @Schema(description = "관광지맵X", example = "126.9769")
    private String tourismMapX;
    @Schema(description = "관광지맵Y", example = "37.5769")
    private String tourismMapY;
    @Schema(description = "뱃지코드", example = "1")
    private String badgeCode;
    private List<MultipartFile> tourismImages;

    public TourismUploadReqDto(TourismInsertDto tourismInsertDto) {
        this.tourismName = tourismInsertDto.getTourismName();
        this.tourismAddress = tourismInsertDto.getTourismAddress();
        this.tourismLink = tourismInsertDto.getTourismLink();
        this.tourismContact = tourismInsertDto.getTourismContact();
        this.tourismImages = tourismInsertDto.getTourismImages();
        this.tourismMapX = tourismInsertDto.getTourismMapX();
        this.tourismMapY = tourismInsertDto.getTourismMapY();
        this.badgeCode = tourismInsertDto.getBadgeCode();
    }
}
