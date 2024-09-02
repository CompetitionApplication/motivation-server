package kr.co.mapper.common;

import kr.co.dto.common.mail.ReservationMailDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailMapper {
    void insertMailSend(ReservationMailDto reservationMailDto);
}
