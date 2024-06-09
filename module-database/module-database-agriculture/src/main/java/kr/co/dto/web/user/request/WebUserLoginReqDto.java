package kr.co.dto.web.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WebUserLoginReqDto {

    @NotNull(message = "Id는 필수입력값입니다.")
    @Schema(description = "ID", example = "testId01")
    private String userWebId;

    @NotNull(message = "pw는 필수입력값입니다.")
    @Schema(description = "PW", example = "qwe123!@#")
    private String userWebPw;

}
