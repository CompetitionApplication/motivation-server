package kr.co.mapper.app;

import kr.co.dto.app.shopping.response.ShoppingGoodsDetailResDto;
import kr.co.dto.app.shopping.response.ShoppingGoodsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingMapper {

    List<ShoppingGoodsDto> selectGoodsForNew(String userEamil, String language);

    List<ShoppingGoodsDto> selectGoodsForMz(String userEamil, String language);

    ShoppingGoodsDetailResDto selectGoodsDetailByGoodsId(String goodsId);

    void insertGoodsVisit(String userId, String goodsId);
}
