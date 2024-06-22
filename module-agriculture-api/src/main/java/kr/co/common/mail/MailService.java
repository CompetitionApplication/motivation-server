package kr.co.common.mail;

import kr.co.dto.common.mail.ReservationMailDto;

public interface MailService {

    void sendReservationMail(ReservationMailDto reservationMailDto) throws Exception;
}
