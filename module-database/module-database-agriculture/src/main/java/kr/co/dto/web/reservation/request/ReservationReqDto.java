package kr.co.dto.web.reservation.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationReqDto {
    @NotNull
    private String farmId;

    @NotNull
    private String reservationName;

    @NotNull
    private String reservationEmail;

    @NotNull
    private String reservationTel;

    @NotNull
    private String reservationDate;

    @NotNull
    private String reservationParticipants;

    @NotNull
    private String reservationStartTime;

    @NotNull
    private String reservationEndTime;
}
