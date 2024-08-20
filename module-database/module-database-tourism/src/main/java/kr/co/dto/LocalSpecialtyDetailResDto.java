package kr.co.dto;

import kr.co.entity.LocalItem;
import lombok.Data;

@Data
public class LocalSpecialtyDetailResDto {
    private String localSpecialtyName;
    private String localSpecialtyPrice;
    private String localSpecialtyStampCount;

    public LocalSpecialtyDetailResDto(LocalItem localItem) {
        this.localSpecialtyName = localItem.getLocalItemName();
        this.localSpecialtyPrice = localItem.getLocalItemPrice();
        this.localSpecialtyStampCount = localItem.getLocalItemStampCount() + "개";
    }
}
