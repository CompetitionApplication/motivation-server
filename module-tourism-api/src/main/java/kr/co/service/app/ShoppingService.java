package kr.co.service.app;

import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.shopping.request.ShoppingGoodsDetailReqDto;
import kr.co.dto.app.shopping.request.ShoppingMainReqDto;
import kr.co.dto.app.shopping.response.ShoppingGoodsDetailResDto;
import kr.co.dto.app.shopping.response.ShoppingGoodsDto;
import kr.co.dto.app.shopping.response.ShoppingMainResDto;
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

        //mz픽 굿즈
        List<ShoppingGoodsDto> mzGoodsList = shoppingMapper.selectGoodsForMz(serviceUser.getUserId(), shoppingMainReqDto.getLanguage());

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

}
