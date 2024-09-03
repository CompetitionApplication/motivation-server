package kr.co.dto.app.home.response;

import lombok.Data;

import java.util.List;

@Data
public class MainResDto {

    private String mainUrl;
    private String badgeCnt;

    private List<TourDto> tourTopList;

    private List<TourDto> suggestionList;
}
