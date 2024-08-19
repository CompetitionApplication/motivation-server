package kr.co.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalSpecialtyResDto {
    private String localSpecialtyId;
    private String localSpecialtyName;
    private String localSpecialtyPrice;
    private String localSpecialtyStampCount;
}
