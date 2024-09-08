package kr.co.dto.app.shopping.response;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingMainResDto {

    private List<ShoppingGoodsDto> newGoodsList;
    private List<ShoppingGoodsDto> mzGoodsList;
    private List<ShoppingGoodsDto> eventGoodsList;
}
