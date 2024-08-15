package kr.co.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class LoginReqDto implements Serializable {

    @Schema(example = "KAKAO")
    private String socialType;
    @Schema(example = "dsadnask")
    private String appDeviceToken;
    @NotNull(message = "userEmail은 필수 입니다.")
    @Schema(example = "test@test.com")
    private String userEmail;
    @Schema(example = "권재은")
    private String userName;
    @Schema(example = "여")
    private String userSex;
    @Schema(example = "20")
    private String userAge;
    @Schema(
            type = "array",
            example = "[\"조용한\", \"편안한\", \"액티비티\"]"
    )
    private List<String> tripStyles;
    @Schema(
            type = "array",
            example = "[\"음악\", \"도서\", \"게임\"]"
    )
    private List<String> hobbyNames;

}
