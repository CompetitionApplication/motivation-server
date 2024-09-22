package kr.co.service.app;

import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.shopping.request.ShoppingGoodsBuyReqDto;
import kr.co.dto.app.shopping.request.ShoppingGoodsDetailReqDto;
import kr.co.dto.app.shopping.request.ShoppingGoodsKeepReqDto;
import kr.co.dto.app.shopping.request.ShoppingMainReqDto;
import kr.co.dto.app.shopping.response.ShoppingGoodsDetailResDto;
import kr.co.dto.app.shopping.response.ShoppingGoodsDto;
import kr.co.dto.app.shopping.response.ShoppingMainResDto;
import kr.co.entity.Goods;
import kr.co.entity.UserCart;
import kr.co.mapper.app.ShoppingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingService {

    private final ShoppingMapper shoppingMapper;

    public ShoppingMainResDto getMain(ServiceUser serviceUser, ShoppingMainReqDto shoppingMainReqDto){
        ShoppingMainResDto r = new ShoppingMainResDto();

        //신규 굿즈
        List<ShoppingGoodsDto> newGoodsList = shoppingMapper.selectGoodsForNew(serviceUser.getUserId(), shoppingMainReqDto.getLanguage());
        r.setNewGoodsList(newGoodsList);

        //mz픽 굿즈
        List<ShoppingGoodsDto> mzGoodsList = shoppingMapper.selectGoodsForMz(serviceUser.getUserId(), shoppingMainReqDto.getLanguage());
        r.setMzGoodsList(mzGoodsList);

        //TODO 이벤트 굿즈

        return r;
    }

    public ShoppingGoodsDetailResDto getGoodsDetail(ServiceUser serviceUser, ShoppingGoodsDetailReqDto shoppingGoodsDetailReqDto){

        //굿즈 디테일 조회
        ShoppingGoodsDetailResDto r = shoppingMapper.selectGoodsDetailByGoodsId(shoppingGoodsDetailReqDto.getGoodsId());

        //조회 카운트
        shoppingMapper.insertGoodsVisit(serviceUser.getUserId(), shoppingGoodsDetailReqDto.getGoodsId());

        return r;
    }

    public void userCart(ShoppingGoodsKeepReqDto shoppingGoodsKeepReqDto, ServiceUser serviceUser){
        //장바구니 조회
        UserCart userCart = shoppingMapper.selectUserCartByGoodsIdAndUserId(shoppingGoodsKeepReqDto.getGoodsId(), serviceUser.getUserId());

        if(userCart == null){
            //장바구니 담기
            shoppingMapper.insertUserCart(shoppingGoodsKeepReqDto.getGoodsId(), serviceUser.getUserId());
        }else {
            //장바구니 담기 수정
            shoppingMapper.updateUserCart(shoppingGoodsKeepReqDto.getGoodsId(), serviceUser.getUserId(), shoppingGoodsKeepReqDto.getDelYn());
        }
    }

    public void goodsBuy(ShoppingGoodsBuyReqDto shoppingGoodsBuyReqDto, ServiceUser serviceUser){
        Goods goods = shoppingMapper.selectGoodsByGoodsId(shoppingGoodsBuyReqDto.getGoodsId());
        if(goods == null){
            throw new CommonException("9999","굿즈 정보를 찾을 수 없습니다.");
        }

        shoppingMapper.insertOrderItem(shoppingGoodsBuyReqDto.getGoodsId(), shoppingGoodsBuyReqDto.getOrderPrice(), shoppingGoodsBuyReqDto.getOrderCount(), serviceUser.getUserId());
    }

}
