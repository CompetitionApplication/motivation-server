package kr.co.service.admin;

import kr.co.auth.TourismUser;
import kr.co.dto.OrderItemResDto;
import kr.co.entity.OrderItem;
import kr.co.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public Page<OrderItemResDto> getOrderItemList(int page, int size) {
        Page<OrderItem> orderItems = orderItemRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));
        List<OrderItemResDto> orderItemResDtos = orderItems.stream()
                .map(orderItem -> OrderItemResDto.builder()
                        .orderId(orderItem.getOrderId())
                        .goodsId(orderItem.getGoods().getGoodsId())
                        .orderDatetime(orderItem.getRegDatetime())
                        .orderUser(orderItem.getUser().getUserName())
                        .orderPrice(orderItem.getOrderPrice() + "원")
                        .orderStatus(orderItem.getOrderStatus().name())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(orderItemResDtos, orderItems.getPageable(), orderItems.getTotalElements());
    }


    public void saveItem(TourismUser tourismUser) {
        orderItemRepository.save(new OrderItem(10000, tourismUser.loginUser.getUser()));
    }
}
