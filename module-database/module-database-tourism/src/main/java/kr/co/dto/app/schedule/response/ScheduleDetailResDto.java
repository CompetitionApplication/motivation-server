package kr.co.dto.app.schedule.response;

import lombok.Data;

@Data
public class ScheduleDetailResDto {

    private String tourismApiId;
    private String title;
    private String favoriteYn;
    private String fileUrl;
    private String visitCnt;
    private String addr;
    private String homePage;
    private String fee;
}
