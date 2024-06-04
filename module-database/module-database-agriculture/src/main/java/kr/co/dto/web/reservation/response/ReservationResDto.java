package kr.co.dto.web.reservation.response;

import lombok.Data;

@Data
public class ReservationResDto {
    private String reservationName;
    private String reservationId;
    private String farmAccountNo;
    private String farmKindNm;
    private String farmName;
    private String reviewStar;
    private String reviewStarCnt;
    private String farmOwnerTel;
    private String farmUseTimeDetail;
    private String reservationDate;
    private String reservationParticipants;
    private String reservationTime;
}
