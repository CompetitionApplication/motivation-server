package kr.co.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "order-item", description = "주문")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/order")
@Slf4j
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Operation(summary = "주문 목록 리스트", description = "주문 목록 리스트 입니다.")
    @GetMapping("/items")
    public ResponseEntity<?> items(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(orderItemService.getItems(page, size));
    }
}
