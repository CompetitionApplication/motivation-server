package kr.co.dto.app.home.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StatusChangeReqDto {

    @NotNull(message = "예약값은 필수입니다.")
    @Schema(description = "예약ID", example = "5fc775a4-265c-11ef-81e3-02001701d75b")
    private String reservationId;

    @NotNull(message = "상태변경값은 필수입니다.")
    @Pattern(regexp = "^(01|02)$", message = "상태변경값은 01,02만 허용됩니다.")
    @Schema(description = "상태값", example = "01", allowableValues = {"01", "02"})
    private String reservationStatus;

}
