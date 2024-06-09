package kr.co.dto.app.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppUserLoginReqDto {

    @NotNull(message = "앱ID는 필수값입니다.")
    @Schema(description = "앱ID", example = "tYagj")
    private String farmAppId;

    @NotNull(message = "앱pw는 필수값입니다.")
    @Schema(description = "앱PW", example = "testpw@@")
    private String farmAppPw;

    @NotNull(message = "앱푸시토큰은 필수값입니다.")
    @Schema(description = "앱푸시토큰", example = "Fm4tSU+9pgD8ym2SxKFHrAFm4tSU+9pgD8ym2SxKFHrAFm4tSU+9pgD8ym2SxKFHrAFm4tSU+9pgD8ym2SxKFHrAasd")
    private String farmAppPushToken;
}
