package kr.co.dto.web.farm.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqDto {
    @NotNull(message = "userEmail은 필수 입니다.")
    private String userEmail;
}
