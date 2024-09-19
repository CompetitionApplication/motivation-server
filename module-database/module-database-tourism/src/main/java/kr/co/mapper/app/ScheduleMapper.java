package kr.co.mapper.app;

import kr.co.dto.app.schedule.response.ScheduleDetailResDto;
import kr.co.dto.app.schedule.response.ScheduleResDto;
import kr.co.entity.TourismFavorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    List<ScheduleResDto> selectTourismSchedule(String userId);

    ScheduleDetailResDto selectTourismScheduleDetail(String userId, String tourismApiId);

    TourismFavorite selectTourismFavoriteByUserIdAndTourismApiId(String userId, String tourismApiId);

    void insertTourismFavorite(String tourismApiId, String userId);

    void updateTourismFavorite(String tourismApiId, String userId, String favoriteYn);

    void insertTourismSchedule(String tourismApiId, String scheduleDate, String userId);

}