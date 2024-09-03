package kr.co.service.app;

import kr.co.auth.TourismUser;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.home.response.MainResDto;
import kr.co.dto.app.home.response.TourDto;
import kr.co.entity.TourismApi;
import kr.co.mapper.app.HomeMapper;
import kr.co.mapper.app.TourismMapper;
import kr.co.mapper.app.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {

    private final HomeMapper homeMapper;
    private final TourismMapper tourismMapper;

    public MainResDto getMain(ServiceUser serviceUser){
        MainResDto r = new MainResDto();

        //뱃지 카운트 조회
        int badgeCnt = homeMapper.selectUserBadgeByUserId(serviceUser.getUserId());
        r.setBadgeCnt(Integer.toString(badgeCnt));

        //추천활동지 조회
        List<TourismApi> tourismApiList = tourismMapper.selectTourismApi();

        //랜덤 5개 추출
        Collections.shuffle(tourismApiList);
        List<TourismApi> randomTourismApiList = tourismApiList.subList(0, 5);

        //엔티티 dto 변환
        List<TourDto> tourSuggestionList = new ArrayList<>();
        for(TourismApi data : randomTourismApiList){
            TourDto tourDto = new TourDto();
            tourDto.setTourName(data.getTitle());
            tourDto.setImageUrl(data.getFirstimage());
            tourSuggestionList.add(tourDto);
        }
        r.setSuggestionList(tourSuggestionList);

        //활동지 top15
        List<TourismApi> tourismApiForTopRankList = tourismMapper.selectTourismApiForTopRank();

        //엔티티 dto 변환
        List<TourDto> tourTopList = new ArrayList<>();
        for(TourismApi data : tourismApiForTopRankList){
            TourDto tourDto = new TourDto();
            tourDto.setTourName(data.getTitle());
            tourDto.setImageUrl(data.getFirstimage());
            tourTopList.add(tourDto);
        }
        r.setTourTopList(tourTopList);

        return r;
    }

}
