package kr.co.common.mail;

public interface MailService {

    void sendReservationMail(ReservationMailDto reservationMailDto) throws Exception;
}
