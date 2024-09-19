package kr.co.mapper.app;

import kr.co.dto.app.home.request.HomeMainReqDto;
import kr.co.entity.TourismApi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourismMapper {

    List<TourismApi> selectTourismApi(HomeMainReqDto homeMainReqDto);

    List<TourismApi> selectTourismApiForTopRank();


}
