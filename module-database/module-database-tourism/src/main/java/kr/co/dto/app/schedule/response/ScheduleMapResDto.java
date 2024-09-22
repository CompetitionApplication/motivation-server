package kr.co.dto.app.schedule.response;

import lombok.Data;

import java.util.List;

@Data
public class ScheduleMapResDto {

    private List<ScheduleResDto> tourList;
    private List<ScheduleResDto> userList;
}
