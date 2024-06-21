package kr.co.app.home.service;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.common.mail.MailService;
import kr.co.common.mail.ReservationMailDto;
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

import java.text.DecimalFormat;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    final HomeMapper homeMapper;
    final FarmMapper farmMapper;
    final AlarmMapper alarmMapper;
    final ReservationMapper reservationMapper;
    final MailService mailService;

    @Override
    public List<HomeResDto> homtList(String homeTab, User user){
        List<HomeResDto> r = homeMapper.homeList(homeTab, user);
        return r;
    }

    @Override
    public void statusChange(StatusChangeReqDto statusChangeReqDto, User user) throws Exception{
        Reservation reservation = reservationMapper.selectReservationByReservationIdForReservation(statusChangeReqDto.getReservationId());

        if(reservation == null){
            throw new CommonException(CommonErrorCode.NOT_FOUND_RESERVATION_ID.getCode(),CommonErrorCode.NOT_FOUND_RESERVATION_ID.getMessage());
        }

        homeMapper.statusChange(statusChangeReqDto, user);

        Farm farm = farmMapper.selectFarmByFarmIdForFarm(reservation.getFarm_id());

        //mail
        int number = Integer.parseInt(farm.getFarm_use_amt());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(number);

        ReservationMailDto reservationMailDto = new ReservationMailDto();
        if(statusChangeReqDto.getReservationStatus().equals("01")){
            reservationMailDto.setTitle("we팜 예약확정 안내 메일");
            reservationMailDto.setMiddleTitle("예약이 확정되었습니다.");
            reservationMailDto.setSmallTitle("안녕하세요, 예약확정된 내용을 안내드립니다.");
        }else{
            reservationMailDto.setTitle("we팜 예약취소 안내 메일");
            reservationMailDto.setMiddleTitle("예약이 취소되었습니다.");
            reservationMailDto.setSmallTitle("안녕하세요, 예약취소된 내용을 안내드립니다.");
        }
        reservationMailDto.setReservationId(reservation.getReservation_id());
        reservationMailDto.setToMail(reservation.getReservation_email());
        reservationMailDto.setUserName(reservation.getReservation_name());
        reservationMailDto.setUserTel(reservation.getReservation_tel());
        reservationMailDto.setFarmName(farm.getFarm_name());
        reservationMailDto.setTotalAmount(formattedNumber+"원");
        reservationMailDto.setReservationDate(reservation.getReservation_date()+" "+reservation.getReservation_start_time()+"~"+reservation.getReservation_end_time());
        mailService.sendReservationMail(reservationMailDto);
    }

}
