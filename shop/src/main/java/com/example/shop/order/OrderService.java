package com.example.shop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 주문 생성/조회/취소에 대한 비즈니스 규칙을 담는 계층.
 * 현재는 저장소/엔티티 미구현이므로 목업 형태로 동작.
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    // private final OrderRepository orderRepository;   // (추가 예정)
    // private final MemberRepository memberRepository; // (추가 예정)
    // private final ProductRepository productRepository; // (추가 예정)

    public Long createOrder(Map<String, Object> body) {
        // TODO:
        // 1) memberId 유효성 검증
        // 2) items(productId, quantity) 각각 재고/가격 확인
        // 3) 총액 계산 / 재고 차감 / 주문 저장
        return 1L; // 목업 ID
    }

    public List<Map<String, Object>> getOrders() {
        // TODO: orderRepository.findAll()
        return Collections.emptyList();
    }

    public Map<String, Object> getOrder(Long orderId) {
        // TODO: orderRepository.findById(orderId)
        return Map.of(
                "id", orderId,
                "memberId", 1L,
                "status", "CREATED",
                "items", List.of()
        );
    }

    public void cancelOrder(Long orderId) {
        // TODO:
        // 1) 주문 상태 확인
        // 2) 취소 처리 및 재고 원복
        // 3) 저장
    }
}