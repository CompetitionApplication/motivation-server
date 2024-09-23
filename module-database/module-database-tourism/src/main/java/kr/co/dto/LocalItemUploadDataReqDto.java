package kr.co.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class LocalItemUploadDataReqDto {

    private String localItemName;
    private String localItemPrice;
    private String areaCodeId;
    private String detailAreaCodeId;
    private List<MultipartFile> localItemImages;


    public LocalItemUploadDataReqDto(LocalItemUploadDataReqDto localItemUploadDataReqDto) {
        this.localItemName = localItemUploadDataReqDto.getLocalItemName();
        this.localItemPrice = localItemUploadDataReqDto.getLocalItemPrice();
        this.areaCodeId = localItemUploadDataReqDto.getAreaCodeId();
        this.detailAreaCodeId = localItemUploadDataReqDto.getDetailAreaCodeId();
    }
}
