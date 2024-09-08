package kr.co.service.app;

import kr.co.dto.app.common.ServiceUser;
import kr.co.dto.app.shopping.response.ShoppingGoodsDto;
import kr.co.dto.app.shopping.response.ShoppingMainResDto;
import kr.co.mapper.app.ShoppingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingService {

    private final ShoppingMapper shoppingMapper;

    public ShoppingMainResDto getMain(ServiceUser serviceUser){
        ShoppingMainResDto r = new ShoppingMainResDto();

        //신규 굿즈
        List<ShoppingGoodsDto> newGoodsList = shoppingMapper.selectGoodsForNew(serviceUser.getUserId());

        //mz픽 굿즈
        //List<ShoppingGoodsDto> mzGoodsList = shoppingMapper.selectGoodsForMz(serviceUser.getUserId());

        //TODO 이벤트 굿즈

        return r;
    }

}
