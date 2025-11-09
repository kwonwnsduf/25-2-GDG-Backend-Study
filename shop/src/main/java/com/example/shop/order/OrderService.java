package com.example.shop.order;

import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import com.example.shop.product.Product;
import com.example.shop.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    /**
     * 주문 생성
     * body 예시:
     * {
     *   "memberId": 1,
     *   "productId": 2,
     *   "totalPrice": 30000,
     *   "pointUsed": 1000,
     *   "cashAmount": 29000,
     *   "status": "ORDERED"
     * }
     */
    @Transactional
    public Long createOrder(Map<String, Object> body) {
        Number memberIdNum = (Number) body.get("memberId");
        Number productIdNum = (Number) body.get("productId");
        Number totalPriceNum = (Number) body.get("totalPrice");

        if (memberIdNum == null || productIdNum == null || totalPriceNum == null) {
            throw new IllegalArgumentException("memberId, productId, totalPrice는 필수 값입니다.");
        }

        Long memberId = memberIdNum.longValue();
        Long productId = productIdNum.longValue();
        int totalPrice = totalPriceNum.intValue();

        int pointUsed = body.get("pointUsed") != null
                ? ((Number) body.get("pointUsed")).intValue()
                : 0;
        int cashAmount = body.get("cashAmount") != null
                ? ((Number) body.get("cashAmount")).intValue()
                : totalPrice;
        String status = body.get("status") != null
                ? (String) body.get("status")
                : "ORDERED";

        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new RuntimeException("주문 회원을 찾을 수 없습니다. id=" + memberId);
        }

        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new RuntimeException("주문 상품을 찾을 수 없습니다. id=" + productId);
        }

        Order order = new Order(member, product, totalPrice, pointUsed, cashAmount, status);
        orderRepository.save(order);
        return order.getId();
    }

    /** 전체 주문 조회 */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return Collections.emptyList();
        }
        return orders.stream()
                .map(this::toMap)
                .collect(Collectors.toList());
    }

    /** 단일 주문 조회 */
    @Transactional(readOnly = true)
    public Map<String, Object> getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다. id=" + orderId);
        }
        return toMap(order);
    }

    /**
     * 주문 수정 (여기서는 상태만 수정한다고 가정)
     * body 예시: { "status": "CANCELLED" }
     */
    @Transactional
    public void updateOrder(Long orderId, Map<String, Object> body) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다. id=" + orderId);
        }

        String status = (String) body.get("status");
        if (status != null) {
            order.updateStatus(status);
        }
    }

    /** 주문 삭제 */
    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new RuntimeException("주문을 찾을 수 없습니다. id=" + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    /** 엔티티 → 응답용 Map 변환 */
    private Map<String, Object> toMap(Order order) {
        return Map.of(
                "id", order.getId(),
                "memberId", order.getMember() != null ? order.getMember().getId() : null,
                "productId", order.getProduct() != null ? order.getProduct().getId() : null,
                "orderDate", order.getOrderDate() != null ? order.getOrderDate().toString() : null,
                "totalPrice", order.getTotalPrice(),
                "pointUsed", order.getPointUsed(),
                "cashAmount", order.getCashAmount(),
                "status", order.getStatus()
        );
    }
}
