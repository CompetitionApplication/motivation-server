package kr.co.web.reservation.service;

import kr.co.dto.web.reservation.request.ReservationCancelReqDto;
import kr.co.dto.web.reservation.request.ReservationReqDto;
import kr.co.dto.web.reservation.response.ReservationHistoryResDto;
import kr.co.dto.web.reservation.response.ReservationResDto;
import kr.co.entity.User;

import java.util.List;

public interface ReservationService {

    ReservationResDto reservationFarm(ReservationReqDto reservationReqDto, User user) throws Exception;
    List<ReservationHistoryResDto> reservationFarmHistory(User user);
    void reservationFarmCancel(ReservationCancelReqDto reservationCancelReqDto, User user) throws Exception;
}
