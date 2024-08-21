package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalSpecialtyResDto {
    @Schema(description = "특산품 ID", example = "1")
    private String localSpecialtyId;
    @Schema(description = "특산품명", example = "한과")
    private String localSpecialtyName;
    @Schema(description = "특산품가격", example = "10000")
    private String localSpecialtyPrice;
    @Schema(description = "특산품재고", example = "100")
    private String localSpecialtyStampCount;
}
