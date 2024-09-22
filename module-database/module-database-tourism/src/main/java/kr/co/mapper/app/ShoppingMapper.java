package kr.co.mapper.app;

import kr.co.dto.app.shopping.response.ShoppingGoodsDetailResDto;
import kr.co.dto.app.shopping.response.ShoppingGoodsDto;
import kr.co.entity.Goods;
import kr.co.entity.UserCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingMapper {

    List<ShoppingGoodsDto> selectGoodsForNew(String userId, String language);

    List<ShoppingGoodsDto> selectGoodsForMz(String userId, String language);

    ShoppingGoodsDetailResDto selectGoodsDetailByGoodsId(String goodsId);

    void insertGoodsVisit(String userId, String goodsId);

    UserCart selectUserCartByGoodsIdAndUserId(String goodsId, String userId);

    void insertUserCart(String goodsId, String orderCount, String userId);

    void updateUserCart(String goodsId, String userId, String delYn);

    void insertOrderItem(String goodsId, String orderPrice, String orderCount, String userId);

    Goods selectGoodsByGoodsId(String goodsId);
}
