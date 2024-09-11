package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.dto.OrderItemResDto;
import kr.co.dto.OrderStatusUpdateReqDto;
import kr.co.service.admin.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "order-item", description = "주문")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/order-item")
@Slf4j
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Operation(summary = "주문 목록 리스트", description = "주문 목록 리스트 입니다.")
    @GetMapping("/list")
    public ResponseEntity<Page<OrderItemResDto>> getOrderItemList(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "7") int size) {
        return ResponseEntity.ok(orderItemService.getOrderItemList(page, size));
    }

    @Operation(summary = "주문 상태 리스트", description = "주문 상태 리스트 입니다.")
    @GetMapping("/status")
    public ResponseEntity<?> getOrderStatusList() {
        return ResponseEntity.ok(orderItemService.getOrderStatusList());
    }

    @Operation(summary = "주문 상태 변경", description = "주문 상태 변경합니다.")
    @PutMapping("")
    public ResponseEntity<?> updateOrderStatus(@RequestBody OrderStatusUpdateReqDto orderStatusUpdateReqDto) {
        orderItemService.updateOrderStatus(orderStatusUpdateReqDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable(value = "orderId") String orderId) {
        orderItemService.deleteOrderItem(orderId);
        return ResponseEntity.ok().build();
    }



}
