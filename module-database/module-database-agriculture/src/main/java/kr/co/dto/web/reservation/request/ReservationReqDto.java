package kr.co.dto.web.reservation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationReqDto {
    @NotNull(message = "농장ID는 필수 입력값입니다.")
    @Schema(description = "농장ID", example = "94a5de3d-2165-11ef-81e3-02001701d75b")
    private String farmId;

    @NotNull(message = "예약자명은 필수 입력값입니다.")
    @Schema(description = "예약자명", example = "아무개")
    private String reservationName;

    @NotNull(message = "예약자이메일은 필수 입력값입니다.")
    @Schema(description = "예약자이메일", example = "test@naver.com")
    private String reservationEmail;

    @NotNull(message = "예약자전화번호는 필수 입력값입니다.")
    @Schema(description = "예약자전화번호", example = "010-1234-9876")
    private String reservationTel;

    @NotNull(message = "예약일자는 필수 입력값입니다.")
    @Schema(description = "예약일자", example = "2024년 7월 12일")
    private String reservationDate;

    @NotNull(message = "예약인원은 필수 입력값입니다.")
    @Schema(description = "예약인원", example = "2")
    private String reservationParticipants;

    @NotNull(message = "예약시작시간은 필수 입력값입니다.")
    @Schema(description = "예약시작시간", example = "10:00")
    private String reservationStartTime;

    //@NotNull(message = "예약종료시간은 필수 입력값입니다.")
    //@Schema(description = "예약종료시간", example = "11:00")
    //private String reservationEndTime;
}
