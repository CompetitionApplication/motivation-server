package kr.co.mapper.web;

import kr.co.dto.web.reservation.request.ReservationReqDto;
import kr.co.dto.web.reservation.response.ReservationHistoryResDto;
import kr.co.dto.web.reservation.response.ReservationResDto;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

    void insertReservation(@Param("reservationReqDto") ReservationReqDto reservationReqDto, @Param("reservationId") String reservationId, @Param("user") User user);
    ReservationResDto selectReservationByReservationId(String reservationId);
    List<ReservationHistoryResDto> selectReservationByUserId(User user);
}
