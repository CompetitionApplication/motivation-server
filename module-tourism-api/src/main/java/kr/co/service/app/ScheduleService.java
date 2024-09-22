package kr.co.service.app;

import kr.co.common.ApiResponseMessage;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.schedule.request.ScheduleDetailReqDto;
import kr.co.dto.app.schedule.request.ScheduleFavoriteReqDto;
import kr.co.dto.app.schedule.request.ScheduleMapReqDto;
import kr.co.dto.app.schedule.request.ScheduleRegReqDto;
import kr.co.dto.app.schedule.response.ScheduleDetailResDto;
import kr.co.dto.app.schedule.response.ScheduleMapResDto;
import kr.co.dto.app.schedule.response.ScheduleResDto;
import kr.co.entity.TourismFavorite;
import kr.co.entity.TourismSchedule;
import kr.co.mapper.app.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleMapper scheduleMapper;

    public ScheduleMapResDto getSchedules(ScheduleMapReqDto scheduleMapReqDto, ServiceUser serviceUser){

        ScheduleMapResDto r = new ScheduleMapResDto();

        //유저 일정 리스트 조회
        List<ScheduleResDto> userList = scheduleMapper.selectTourismSchedule(serviceUser.getUserId());
        r.setUserList(userList);

        //관광지 조회
        List<ScheduleResDto> tourList = scheduleMapper.selectTourismApi(scheduleMapReqDto.getLanguage(), serviceUser.getUserId());
        r.setTourList(tourList);

        return r;
    }

    public ScheduleDetailResDto getScheduleDetail(ScheduleDetailReqDto scheduleDetailReqDto, ServiceUser serviceUser){

        //일정 상세 조회
        ScheduleDetailResDto r = scheduleMapper.selectTourismScheduleDetail(serviceUser.getUserId(), scheduleDetailReqDto.getTourismApiId());

        return r;
    }

    public void scheduleFavorite(ScheduleFavoriteReqDto scheduleFavoriteReqDto, ServiceUser serviceUser){
        //즐겨찾기 조회
        TourismFavorite tourismFavorite = scheduleMapper.selectTourismFavoriteByUserIdAndTourismApiId(serviceUser.getUserId(), scheduleFavoriteReqDto.getTourismApiId());

        if(tourismFavorite == null){
            //일정 즐겨찾기
            scheduleMapper.insertTourismFavorite(scheduleFavoriteReqDto.getTourismApiId(), serviceUser.getUserId());
        }else{
            //일정 즐겨찾기 수정
            scheduleMapper.updateTourismFavorite(scheduleFavoriteReqDto.getTourismApiId(), serviceUser.getUserId(), scheduleFavoriteReqDto.getFavoriteYn());
        }
    }

    public void scheduleReg(ScheduleRegReqDto scheduleRegReqDto, ServiceUser serviceUser){

        //일정 등록 조회
        TourismSchedule tourismSchedule = scheduleMapper.selectTourismScheduleByUserIdAndTourismApiId(serviceUser.getUserId(), scheduleRegReqDto.getTourismApiId());

        //신규
        if(tourismSchedule == null){
            //일정 등록
            scheduleMapper.insertTourismSchedule(scheduleRegReqDto.getTourismApiId(), scheduleRegReqDto.getScheduledDate(), serviceUser.getUserId());

        //수정
        }else{
            //일정 삭제
            if("Y".equals(scheduleRegReqDto.getDelYn())){
                //일정 삭제
                scheduleMapper.updateTourismSchedule(serviceUser.getUserId(), scheduleRegReqDto.getTourismApiId(),scheduleRegReqDto.getDelYn());
            //일정 등록
            }else{
                //중복 등록 체크
                if("N".equals(tourismSchedule.getDelYn())){
                    throw new CommonException("9999", "이미 등록된 일정 입니다.");
                }else{
                    scheduleMapper.updateTourismSchedule(serviceUser.getUserId(), scheduleRegReqDto.getTourismApiId(),scheduleRegReqDto.getDelYn());
                }
            }
        }
    }


}
