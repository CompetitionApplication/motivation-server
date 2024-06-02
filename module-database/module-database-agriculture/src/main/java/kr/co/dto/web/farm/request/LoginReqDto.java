package kr.co.dto.web.farm.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqDto implements Serializable {
    @NotNull(message = "userEmail은 필수 입니다.")
    private String userEmail;
    private String userName;
    private String userSex;
    private String userAge;
    private String userTourPeriod;
}
