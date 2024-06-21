package kr.co.dto.web.reservation.response;

import lombok.Data;

@Data
public class ReservationHistoryResDto {
    private String reservationId;
    private String reservationStatus;
    private String reservationStatusNm;
    private String farmAccountNo;
    private String farmKindNm;
    private String farmName;
    private String farmZip;
    private String farmOwnerTel;
    private String farmUseTimeDetail;
    private String reservationDate;
    private String reservationParticipants;
    private String reservationStartTime;
    private String reservationEndTime;
    private String reservationName;
    private String reservationEmail;
    private String reservationTel;
    private String farmUseAmt;
    private String farmMainImageUrl;
    private String farmUseDay;
}
