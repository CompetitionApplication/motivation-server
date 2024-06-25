package kr.co.common.mail;

import kr.co.dto.common.mail.ReservationMailDto;
import org.springframework.scheduling.annotation.Async;

public interface MailService {

    @Async
    void sendReservationMail(ReservationMailDto reservationMailDto) throws Exception;
}
