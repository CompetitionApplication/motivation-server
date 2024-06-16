package kr.co.web.reservation.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.web.farm.response.FarmUseTimeDetailResDto;
import kr.co.dto.web.reservation.request.ReservationCancelReqDto;
import kr.co.dto.web.reservation.request.ReservationReqDto;
import kr.co.dto.web.reservation.response.ReservationHistoryResDto;
import kr.co.dto.web.reservation.response.ReservationResDto;
import kr.co.entity.Farm;
import kr.co.entity.Reservation;
import kr.co.entity.User;
import kr.co.mapper.web.CommonMapper;
import kr.co.mapper.web.FarmMapper;
import kr.co.mapper.web.ReservationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    final ReservationMapper reservationMapper;
    final CommonMapper commonMapper;
    final FarmMapper farmMapper;

    @Override
    public ReservationResDto reservationFarm(ReservationReqDto reservationReqDto, User user){
        Farm farm = farmMapper.selectFarmByFarmIdForFarm(reservationReqDto.getFarmId());
        if(farm == null){
            throw new CommonException(CommonErrorCode.FARM_NOT_FOUND.getCode(),CommonErrorCode.FARM_NOT_FOUND.getMessage());
        }

        //체험시간이 영업시간내 시간인지 체크
        List<FarmUseTimeDetailResDto> farmUseTimeDetailList = farmMapper.selectFarmUseTimeDetailList(farm.getFarm_id());
        boolean containsTime = farmUseTimeDetailList.stream()
                .anyMatch(item -> item.getFarmUseTimeDetailSlot().equals(reservationReqDto.getReservationStartTime()));

        if(!containsTime){
            throw new CommonException(CommonErrorCode.CHECK_FARM_USE_TIME.getCode(), CommonErrorCode.CHECK_FARM_USE_TIME.getMessage());
        }

        //종료시간 자동 계산
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(reservationReqDto.getReservationStartTime(), timeFormatter);

        double interval = Double.parseDouble(farm.getFarm_use_time_detail());
        int hours = (int) interval;
        int minutes = (int) ((interval - hours) * 60);

        LocalTime newTime = time.plusHours(hours).plusMinutes(minutes);

        String reservationEndTime = newTime.format(timeFormatter);

        //중복예약 방어로직
        int duplicationReservationCnt = reservationMapper.duplicationReservationCnt(reservationReqDto,user,reservationEndTime);
        if(duplicationReservationCnt > 0){
            throw new CommonException(CommonErrorCode.DUPLICATION_RESERVATION_TIME.getCode(),CommonErrorCode.DUPLICATION_RESERVATION_TIME.getMessage());
        }

        //예약
        String reservationId = commonMapper.selectUUID();
        reservationMapper.insertReservation(reservationReqDto,reservationId,user,reservationEndTime);
        ReservationResDto r = reservationMapper.selectReservationByReservationId(reservationId);
        return r;
    }

    @Override
    public List<ReservationHistoryResDto> reservationFarmHistory(User user){
        List<ReservationHistoryResDto> r = reservationMapper.selectReservationByUserId(user);
        return r;
    }

    @Override
    public void reservationFarmCancel(ReservationCancelReqDto reservationCancelReqDto, User user){
        Reservation reservation = reservationMapper.selectReservationByReservationIdForReservation(reservationCancelReqDto.getReservationId());

        if(reservation == null){
            throw new CommonException(CommonErrorCode.NOT_FOUND_RESERVATION_ID.getCode(),CommonErrorCode.NOT_FOUND_RESERVATION_ID.getMessage());
        }

        reservationMapper.reservationFarmCancel(reservationCancelReqDto,user);
    }
}
