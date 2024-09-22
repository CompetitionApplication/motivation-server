package kr.co.service.admin;

import kr.co.auth.TourismUser;
import kr.co.common.CommonErrorCode;
import kr.co.common.CommonException;
import kr.co.dto.OrderItemResDto;
import kr.co.dto.OrderStatusUpdateReqDto;
import kr.co.entity.OrderItem;
import kr.co.entity.OrderStatus;
import kr.co.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public Page<OrderItemResDto> getOrderItemList(int page, int size) {
        Page<OrderItem> orderItems = orderItemRepository.findAllByDelYnFalse(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "regDatetime")));
        List<OrderItemResDto> orderItemResDtos = orderItems.stream()
                .map(orderItem -> OrderItemResDto.builder()
                        .orderId(orderItem.getOrderItemId())
                        .goodsId(orderItem.getGoods().getGoodsId())
                        .goodsName(orderItem.getGoods().getGoodsName())
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

    public Map<Object, String> getOrderStatusList() {
        Map<Object, String> orderStatusList = new HashMap<>();
        orderStatusList.put("01", "결제완료");
        orderStatusList.put("02", "배송중");
        orderStatusList.put("03", "배송완료");
        return orderStatusList;
    }

    @Transactional
    public void updateOrderStatus(OrderStatusUpdateReqDto orderStatusUpdateReqDto) {
        OrderItem orderItem = orderItemRepository.findById(orderStatusUpdateReqDto.getOrderItemId())
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ORDER.getCode(), CommonErrorCode.NOT_FOUND_ORDER.getMessage()));
        orderItem.updateOrderStatus(OrderStatus.valueOf(orderStatusUpdateReqDto.getOrderStatus()));
    }

    @Transactional
    public void deleteOrderItem(String orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ORDER.getCode(), CommonErrorCode.NOT_FOUND_ORDER.getMessage()));
        orderItem.deleteOrderItem();
    }

    @Transactional
    public void deleteMultiOrderItem(List<String> orderItemIds) {
        orderItemIds.forEach(orderItemId -> {
            OrderItem orderItem = orderItemRepository.findById(orderItemId)
                    .orElseThrow(() -> new CommonException(CommonErrorCode.NOT_FOUND_ORDER.getCode(), CommonErrorCode.NOT_FOUND_ORDER.getMessage()));
            orderItem.deleteOrderItem();
        });
    }
}