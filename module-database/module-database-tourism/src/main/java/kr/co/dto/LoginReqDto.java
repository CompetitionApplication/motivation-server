package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class LoginReqDto implements Serializable {
    @NotNull(message = "userEmail은 필수 입니다.")
    @Schema(example = "test@test.com")
    private String userEmail;
}
