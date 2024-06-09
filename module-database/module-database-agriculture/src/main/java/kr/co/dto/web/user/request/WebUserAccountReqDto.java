package kr.co.dto.web.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WebUserAccountReqDto {

    @NotNull(message = "Id는 필수입력값입니다.")
    @Schema(description = "ID", example = "testId01")
    private String userWebId;

    @NotNull(message = "pw는 필수입력값입니다.")
    @Schema(description = "PW", example = "qwe123!@#")
    private String userWebPw;

    @NotNull(message = "이름은 필수입력값입니다.")
    @Schema(description = "이름", example = "아무개")
    private String userName;

    @NotNull(message = "전화번호는 필수입력값입니다.")
    @Schema(description = "전화번호", example = "01012349876")
    private String userTel;

    @NotNull(message = "이메일은 필수입력값입니다.")
    @Schema(description = "email", example = "test@naver.com")
    private String userEmail;

    @NotNull(message = "생년월일은 필수입력값입니다.")
    @Schema(description = "생년월일", example = "19910911")
    private String userBirth;
}
