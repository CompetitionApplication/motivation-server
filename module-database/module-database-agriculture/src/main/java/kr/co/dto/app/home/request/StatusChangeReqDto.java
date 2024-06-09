package kr.co.dto.app.home.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusChangeReqDto {

    @NotNull(message = "상태변경값은 필수입니다.")
    private String reservationStatus;

    @NotNull(message = "예약값은 필수입니다.")
    private String reservationId;
}
