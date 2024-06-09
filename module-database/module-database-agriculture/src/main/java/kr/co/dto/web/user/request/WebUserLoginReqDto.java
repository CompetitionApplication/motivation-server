package kr.co.dto.web.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WebUserLoginReqDto {

    @NotNull(message = "Id는 필수입력값입니다.")
    private String userWebId;

    @NotNull(message = "pw는 필수입력값입니다.")
    private String userWebPw;

}
