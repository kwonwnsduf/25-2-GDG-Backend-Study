package com.example.shop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /** 주문 생성 */
    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody Map<String, Object> body) {
        Long id = orderService.createOrder(body);
        return ResponseEntity.ok(id);
    }

    /** 주문 전체 조회 */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    /** 주문 단건 조회 */
    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    /** 주문 수정(상태 변경 등) */
    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrder(
            @PathVariable Long orderId,
            @RequestBody Map<String, Object> body
    ) {
        orderService.updateOrder(orderId, body);
        return ResponseEntity.ok().build();
    }

    /** 주문 삭제 */
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
