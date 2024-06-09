package kr.co.dto.app.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppUserLoginReqDto {

    @NotNull
    private String farmAppId;

    @NotNull
    private String farmAppPw;

    @NotNull
    private String farmAppPushToken;
}
