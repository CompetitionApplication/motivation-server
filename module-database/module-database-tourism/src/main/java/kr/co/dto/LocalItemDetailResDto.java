package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.entity.LocalItem;
import lombok.Data;

@Data
public class LocalItemDetailResDto {
    @Schema(description = "특산품명", example = "한과")
    private String localItemName;
    @Schema(description = "특산품가격", example = "10000")
    private String localItemPrice;
    @Schema(description = "지역코드", example = "1")
    private String areaCodeId;
    @Schema(description = "상세지역코드", example = "1")
    private String detailAreaCodeId;

    public LocalItemDetailResDto(LocalItem localItem) {
        this.localItemName = localItem.getLocalItemName();
        this.localItemPrice = localItem.getLocalItemPrice();
        this.areaCodeId = localItem.getAreaCode().getName();
        this.detailAreaCodeId = localItem.getDetailAreaCode().getName();
    }
}
