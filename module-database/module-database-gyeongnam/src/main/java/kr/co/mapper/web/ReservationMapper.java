package kr.co.mapper.web;

import kr.co.dto.web.reservation.request.ReservationCancelReqDto;
import kr.co.dto.web.reservation.request.ReservationReqDto;
import kr.co.dto.web.reservation.response.ReservationHistoryResDto;
import kr.co.dto.web.reservation.response.ReservationResDto;
import kr.co.entity.Reservation;
import kr.co.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

    void insertReservation(@Param("reservationReqDto") ReservationReqDto reservationReqDto, @Param("reservationId") String reservationId, @Param("user") User user, @Param("reservationEndTime") String reservationEndTime);
    ReservationResDto selectReservationByReservationId(String reservationId);
    List<ReservationHistoryResDto> selectReservationByUserId(User user);
    void reservationFarmCancel(@Param("reservationCancelReqDto") ReservationCancelReqDto reservationCancelReqDto, @Param("user") User user);
    Reservation selectReservationByReservationIdForReservation(String reservationId);
    int duplicationReservationCnt(@Param("reservationReqDto") ReservationReqDto reservationReqDto, @Param("user") User user, @Param("reservationEndTime") String reservationEndTime);
    int useDayChk(String day, String farmId);

}
