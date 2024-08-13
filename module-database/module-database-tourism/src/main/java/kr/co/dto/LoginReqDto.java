package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqDto implements Serializable {
    @NotNull(message = "userEmail은 필수 입니다.")
    @Schema(example = "test@test.com")
    private String userEmail;
    @Schema(example = "권재은")
    private String userName;
    @Schema(example = "여")
    private String userSex;
    @Schema(example = "20")
    private String userAge;
    @Schema(example = "투어기간")
    private String userTourPeriod;
}
