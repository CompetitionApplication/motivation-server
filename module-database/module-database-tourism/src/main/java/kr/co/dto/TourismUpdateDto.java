package kr.co.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class TourismUpdateDto {
    private String tourismName;
    private String tourismAddress;
    private String tourismLink;
    private String tourismContact;
    private String areaCodeId;
    private String detailAreaCodeId;
    private String tourismMapX;
    private String tourismMapY;
    private String badgeCode;
    private List<MultipartFile> tourismImages;
}
