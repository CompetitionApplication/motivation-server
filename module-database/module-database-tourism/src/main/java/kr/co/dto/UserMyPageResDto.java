package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserMyPageResDto {
    @Schema(example = "권재은")
    private String userName;
    @Schema(example = "5")
    private String userGetBadgeCount;
}
