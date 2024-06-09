package kr.co.dto.web.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WebUserAccountReqDto {

    @NotNull(message = "Id는 필수입력값입니다.")
    private String userWebId;

    @NotNull(message = "pw는 필수입력값입니다.")
    private String userWebPw;

    @NotNull(message = "이름은 필수입력값입니다.")
    private String userName;

    @NotNull(message = "전화번호는 필수입력값입니다.")
    private String userTel;

    @NotNull(message = "이메일은 필수입력값입니다.")
    private String userEmail;

    @NotNull(message = "생년월일은 필수입력값입니다.")
    private String userBirth;
}
