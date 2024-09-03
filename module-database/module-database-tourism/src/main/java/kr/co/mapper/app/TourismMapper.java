package kr.co.mapper.app;

import kr.co.entity.TourismApi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourismMapper {

    List<TourismApi> selectTourismApi();

    List<TourismApi> selectTourismApiForTopRank();


}
