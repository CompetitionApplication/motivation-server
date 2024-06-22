package kr.co.web.reservation.service;

import com.google.firebase.messaging.FirebaseMessaging;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.common.mail.MailService;
import kr.co.dto.common.mail.ReservationMailDto;
import kr.co.dto.web.farm.response.FarmUseTimeDetailResDto;
import kr.co.dto.web.reservation.request.ReservationCancelReqDto;
import kr.co.dto.web.reservation.request.ReservationReqDto;
import kr.co.dto.web.reservation.response.ReservationHistoryResDto;
import kr.co.dto.web.reservation.response.ReservationResDto;
import kr.co.entity.Alarm;
import kr.co.entity.Farm;
import kr.co.entity.Reservation;
import kr.co.entity.User;
import kr.co.mapper.app.AlarmMapper;
import kr.co.mapper.common.CommonMapper;
import kr.co.mapper.web.FarmMapper;
import kr.co.mapper.web.ReservationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
    final AlarmMapper alarmMapper;
    final MailService mailService;
    final FirebaseMessaging firebaseMessaging;


    @Override
    @Transactional
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

        //예약날짜가 운영요일인지 체크
        // 날짜 형식을 위한 포맷터 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 문자열을 LocalDate 객체로 변환
        LocalDate date = LocalDate.parse(reservationReqDto.getReservationDate(), formatter);

        // 요일 구하기
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String[] days = {"월", "화", "수", "목", "금", "토", "일"};

        String day = days[dayOfWeek.getValue() - 1];
        int useDayChk = reservationMapper.useDayChk(day,reservationReqDto.getFarmId());

        if(useDayChk == 0){
            throw new CommonException(CommonErrorCode.NOT_USE_DAY_RESERVATION.getCode(),CommonErrorCode.NOT_USE_DAY_RESERVATION.getMessage());
        }

        //예약
        String reservationId = commonMapper.reservationId();
        reservationMapper.insertReservation(reservationReqDto,reservationId,user,reservationEndTime);
        ReservationResDto r = reservationMapper.selectReservationByReservationId(reservationId);

        //push
        User farmUser = new User();
        farmUser.setUser_id(farm.getFarm_id());
        int alarmCnt = alarmMapper.selectAlarmCount(farmUser);
        String pushTitle = "예약 알림 도착";
        String pushContent = reservationReqDto.getReservationDate()+" "+reservationReqDto.getReservationName()+"님 예약이 도착했습니다.";
        try {
            //firebaseMessaging.send(makeMessage(farm.getFarm_app_push_token(), pushTitle, pushContent, alarmCnt));
            Alarm alarm = new Alarm();
            alarm.setFarm_id(farm.getFarm_id());
            alarm.setAlarm_kind("01");
            alarm.setAlarm_title(pushTitle);
            alarm.setAlarm_content(pushContent);
            alarm.setCreated_id(user.getUser_id());
            alarmMapper.insertAlarm(alarm);
        }catch (Exception e){
            throw new CommonException(CommonErrorCode.FAIL.getCode(),CommonErrorCode.FAIL.getMessage());
        }

        return r;
    }

    @Override
    public List<ReservationHistoryResDto> reservationFarmHistory(User user){
        List<ReservationHistoryResDto> r = reservationMapper.selectReservationByUserId(user);
        return r;
    }

    @Override
    @Transactional
    public void reservationFarmCancel(ReservationCancelReqDto reservationCancelReqDto, User user) throws Exception{
        Reservation reservation = reservationMapper.selectReservationByReservationIdForReservation(reservationCancelReqDto.getReservationId());

        if(reservation == null){
            throw new CommonException(CommonErrorCode.NOT_FOUND_RESERVATION_ID.getCode(),CommonErrorCode.NOT_FOUND_RESERVATION_ID.getMessage());
        }

        reservationMapper.reservationFarmCancel(reservationCancelReqDto,user);

        Farm farm = farmMapper.selectFarmByFarmIdForFarm(reservation.getFarm_id());

        //push
        User farmUser = new User();
        farmUser.setUser_id(farm.getFarm_id());
        int alarmCnt = alarmMapper.selectAlarmCount(farmUser);
        String pushTitle = "예약취소 알림 도착";
        String pushContent = reservation.getReservation_date()+" "+reservation.getReservation_name()+"님 예약이 취소되었습니다.";
        try {
            //firebaseMessaging.send(makeMessage(farm.getFarm_app_push_token(), pushTitle, pushContent, alarmCnt));
            Alarm alarm = new Alarm();
            alarm.setFarm_id(farm.getFarm_id());
            alarm.setAlarm_kind("01");
            alarm.setAlarm_title(pushTitle);
            alarm.setAlarm_content(pushContent);
            alarm.setCreated_id(user.getUser_id());
            alarmMapper.insertAlarm(alarm);
        }catch (Exception e){
            throw new CommonException(CommonErrorCode.FAIL.getCode(),CommonErrorCode.FAIL.getMessage());
        }

        //mail
        int number = Integer.parseInt(farm.getFarm_use_amt());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(number);

        ReservationMailDto reservationMailDto = new ReservationMailDto();
        reservationMailDto.setUserId(reservation.getUser_id());
        reservationMailDto.setTitle("[we팜] 예약취소 안내 메일");
        reservationMailDto.setMiddleTitle("예약취소");
        reservationMailDto.setSmallTitle("안녕하세요, 예약취소된 내용을 안내드립니다.");
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
