package kr.co.service.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.entity.TourismApi;
import lombok.Data;

import java.util.List;

@Data
public class TourismApiDetailResDto {
    @Schema(description = "여행지명", example = "경복궁")
    private String tourismName;
    @Schema(description = "여행지주소", example = "서울특별시 종로구 사직로 161")
    private String tourismAddress;
    @Schema(description = "여행지링크", example = "https://www.royalpalace.go.kr/")
    private String tourismLink;
    @Schema(description = "여행지연락처", example = "02-3700-3900")
    private String tourismContact;
    private List<String> fileUrl;

    public TourismApiDetailResDto(TourismApi tourismApi, List<String> fileUrl){
        this.tourismName = tourismApi.getTitle();
        this.tourismAddress = tourismApi.getAddr1();
        this.tourismLink = tourismApi.getTourismLink();
        this.tourismContact = tourismApi.getTel();
        this.fileUrl = fileUrl;

    }
}
