package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalItemResDto {
    @Schema(description = "특산품 ID", example = "1")
    private String localItemId;
    @Schema(description = "특산품명", example = "한과")
    private String localItemName;
    @Schema(description = "특산품가격", example = "10000")
    private String localItemPrice;
    @Schema(description = "지역코드", example = "1")
    private String areaCodeId;
    @Schema(description = "상세지역코드", example = "1")
    private String detailAreaCodeId;
}

