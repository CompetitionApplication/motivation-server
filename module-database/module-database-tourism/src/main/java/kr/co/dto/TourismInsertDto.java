package kr.co.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class TourismInsertDto {
    private String tourismName;
    private String tourismAddress;
    private String tourismLink;
    private String tourismContact;
    private String tourismMapX;
    private String tourismMapY;
    private String badgeCode;
    private List<MultipartFile> tourismImages;
}
