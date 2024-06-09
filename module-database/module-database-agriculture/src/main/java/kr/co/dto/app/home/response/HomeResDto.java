package kr.co.dto.app.home.response;

import lombok.Data;

@Data
public class HomeResDto {

    private String homeTab;
    private String userName;
    private String userTel;
    private String reservationParticipants;
    private String reservationDate;
    private String timeAgo;

}
