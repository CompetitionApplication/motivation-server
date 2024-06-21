package kr.co.common.mail;

import lombok.Data;

@Data
public class ReservationMailDto {

    private String title;
    private String toMail;
    private String reservationId;
    private String farmName;
    private String reservationDate;
    private String totalAmount;
    private String userName;
    private String userTel;
}
