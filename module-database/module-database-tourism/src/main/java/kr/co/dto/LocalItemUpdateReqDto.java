package kr.co.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class LocalItemUpdateReqDto {

    private String localItemName;
    private String localItemPrice;
    private String areaCodeId;
    private String detailAreaCodeId;
    private List<MultipartFile> localItemImages;


    public LocalItemUpdateReqDto(LocalItemUpdateReqDto localItemUploadReqDto) {
        this.localItemName = localItemUploadReqDto.getLocalItemName();
        this.localItemPrice = localItemUploadReqDto.getLocalItemPrice();
        this.areaCodeId = localItemUploadReqDto.getAreaCodeId();
        this.detailAreaCodeId = localItemUploadReqDto.getDetailAreaCodeId();
    }
}
