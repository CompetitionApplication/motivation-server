package kr.co.dto;

import kr.co.entity.LocalSpecialty;
import lombok.Data;

@Data
public class LocalSpecialtyDetailResDto {
    private String localSpecialtyName;
    private String localSpecialtyPrice;
    private String localSpecialtyStampCount;

    public LocalSpecialtyDetailResDto(LocalSpecialty localSpecialty) {
        this.localSpecialtyName = localSpecialty.getLocalSpecialtyName();
        this.localSpecialtyPrice = localSpecialty.getLocalSpecialtyPrice();
        this.localSpecialtyStampCount = localSpecialty.getLocalSpecialtyStampCount() + "개";
    }
}
