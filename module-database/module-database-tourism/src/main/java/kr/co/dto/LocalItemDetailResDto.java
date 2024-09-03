package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.entity.LocalItem;
import lombok.Data;

@Data
public class LocalItemDetailResDto {
    @Schema(description = "특산품명", example = "한과")
    private String localSpecialtyName;
    @Schema(description = "특산품가격", example = "10000")
    private String localSpecialtyPrice;
    @Schema(description = "특산품스탬프개수", example = "10개")
    private String localSpecialtyStampCount;

    public LocalItemDetailResDto(LocalItem localItem) {
        this.localSpecialtyName = localItem.getLocalItemName();
        this.localSpecialtyPrice = localItem.getLocalItemPrice();
        this.localSpecialtyStampCount = localItem.getLocalItemStampCount() + "개";
    }
}
