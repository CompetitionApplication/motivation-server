package kr.co.service.app;

import io.micrometer.common.util.StringUtils;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.home.request.HomeMainReqDto;
import kr.co.dto.app.home.request.OpenGoodsReqDto;
import kr.co.dto.app.home.response.*;
import kr.co.entity.TourismApi;
import kr.co.mapper.app.HomeMapper;
import kr.co.mapper.app.TourismMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeService {

    private final HomeMapper homeMapper;
    private final TourismMapper tourismMapper;

    public MainResDto getMain(ServiceUser serviceUser, HomeMainReqDto homeMainReqDto){
        MainResDto r = new MainResDto();

        //뱃지 카운트 조회
        int badgeCnt = homeMapper.selectUserBadgeByUserIdForCnt(serviceUser.getUserId());
        r.setBadgeCnt(Integer.toString(badgeCnt));

        //추천활동지 조회
        List<TourDto> tourismApiList = tourismMapper.selectTourismApi(homeMainReqDto);

        //랜덤 5개 추출
        Collections.shuffle(tourismApiList);
        List<TourDto> randomTourismApiList = tourismApiList.subList(0, 5);

        //엔티티 dto 변환
        r.setSuggestionList(randomTourismApiList);

        //활동지 top15
        List<TourDto> tourismApiForTopRankList = tourismMapper.selectTourismApiForTopRank();

        //top 15 없다면 랜덤 15개
        if(tourismApiForTopRankList.size() == 0){
            int size = 15;
            if(tourismApiList.size() < 15){
                size = tourismApiList.size();
            }
            tourismApiForTopRankList = tourismApiList.subList(0, size);
        }

        //엔티티 dto 변환
        r.setTourTopList(tourismApiForTopRankList);

        //굿즈 리스트
        List<OpenGoodsResDto> goodsList = homeMapper.selectGoodsListForOpenYn(serviceUser.getUserId(),homeMainReqDto.getLanguage());

        //오픈 굿즈
        List<OpenGoodsResDto> openGoodsList = goodsList.stream()
                .filter(obj -> "Y".equals(obj.getGoodsOpenYn()))
                .collect(Collectors.toList());

        r.setOpenGoodsList(openGoodsList);

        //미오픈 굿즈
        List<OpenGoodsResDto> notOpenGoodsList = goodsList.stream()
                .filter(obj -> "N".equals(obj.getGoodsOpenYn()))
                .collect(Collectors.toList());

        r.setNotOpenGoodsList(notOpenGoodsList);

        return r;
    }

    public List<OpenGoodsResDto> getPossibleBuy(OpenGoodsReqDto openGoodsReqDto, ServiceUser serviceUser){

        //굿즈 리스트
        List<OpenGoodsResDto> r = homeMapper.selectGoodsListForOpenYn(serviceUser.getUserId(),openGoodsReqDto.getLanguage());

        //오픈,미오픈 굿즈
        r.removeIf(obj -> !openGoodsReqDto.getOpenYn().equals(obj.getGoodsOpenYn()));

        return r;
    }

    public CollectBadgeResDto getCollectBadges(ServiceUser serviceUser){

        CollectBadgeResDto r = new CollectBadgeResDto();

        //획득 뱃지 리스트
        List<CollectBadgeDto> collectBadgeDtoList = homeMapper.selectUserBadgeByUserIdForCollect(serviceUser.getUserId());
        r.setCollectBadgeDtoList(collectBadgeDtoList);

        //총 획득 뱃지 카운트
        int totalCollectBadgeCnt = 0;
        for(CollectBadgeDto data : collectBadgeDtoList){
            totalCollectBadgeCnt += Integer.parseInt(data.getCnt());
        }
        r.setTotalCollectBadgeCnt(Integer.toString(totalCollectBadgeCnt));

        return r;
    }

}
