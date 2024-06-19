package kr.co.app.home.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.home.request.StatusChangeReqDto;
import kr.co.dto.app.home.response.HomeResDto;
import kr.co.entity.*;
import kr.co.mapper.app.AlarmMapper;
import kr.co.mapper.app.HomeMapper;
import kr.co.mapper.web.FarmMapper;
import kr.co.mapper.web.ReservationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    final HomeMapper homeMapper;
    final FarmMapper farmMapper;
    final AlarmMapper alarmMapper;
    final ReservationMapper reservationMapper;

    @Override
    public List<HomeResDto> homtList(String homeTab){
        List<HomeResDto> r = homeMapper.homeList(homeTab);
        return r;  
    }

    @Override
    public void statusChange(StatusChangeReqDto statusChangeReqDto, User user){
        Reservation reservation = reservationMapper.selectReservationByReservationIdForReservation(statusChangeReqDto.getReservationId());

        if(reservation == null){
            throw new CommonException(CommonErrorCode.NOT_FOUND_RESERVATION_ID.getCode(),CommonErrorCode.NOT_FOUND_RESERVATION_ID.getMessage());
        }

        homeMapper.statusChange(statusChangeReqDto, user);
    }

}
