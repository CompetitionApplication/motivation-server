package kr.co.service.app;

import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.schedule.request.ScheduleDetailReqDto;
import kr.co.dto.app.schedule.request.ScheduleFavoriteReqDto;
import kr.co.dto.app.schedule.request.ScheduleRegReqDto;
import kr.co.dto.app.schedule.response.ScheduleDetailResDto;
import kr.co.dto.app.schedule.response.ScheduleResDto;
import kr.co.entity.TourismFavorite;
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

    public List<ScheduleResDto> getSchedules(ServiceUser serviceUser){

        //일정 리스트 조회
        List<ScheduleResDto> r = scheduleMapper.selectTourismSchedule(serviceUser.getUserId());

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
        //일정 등록
        scheduleMapper.insertTourismSchedule(scheduleRegReqDto.getTourismApiId(), scheduleRegReqDto.getScheduledDate(), serviceUser.getUserId());
    }


}
