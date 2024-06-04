package kr.co.web.reservation.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.web.reservation.request.ReservationReqDto;
import kr.co.dto.web.reservation.response.ReservationHistoryResDto;
import kr.co.dto.web.reservation.response.ReservationResDto;
import kr.co.entity.Farm;
import kr.co.entity.File;
import kr.co.entity.User;
import kr.co.mapper.CommonMapper;
import kr.co.mapper.FarmMapper;
import kr.co.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        String reservationId = commonMapper.selectUUID();
        reservationMapper.insertReservation(reservationReqDto,reservationId,user);
        ReservationResDto r = reservationMapper.selectReservationByReservationId(reservationId);
        return r;
    }

    @Override
    public List<ReservationHistoryResDto> reservationFarmHistory(User user){
        List<ReservationHistoryResDto> r = reservationMapper.selectReservationByUserId(user);
        return r;
    }
}
