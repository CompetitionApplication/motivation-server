package kr.co.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class LocalItemUploadDataDto {
//test
    private String localItemName;
    private String localItemPrice;
    private String areaCodeId;
    private String detailAreaCodeId;
    private List<MultipartFile> localItemImages;
}
