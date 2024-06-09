package kr.co.dto.web.reservation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationCancelReqDto {

    @NotNull(message = "예약ID는 필수 입력값입니다.")
    @Schema(description = "예약ID", example = "29af2259-2663-11ef-81e3-02001701d75b")
    private String reservationId;
}
