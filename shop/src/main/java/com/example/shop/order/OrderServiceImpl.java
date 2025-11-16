package com.example.shop.order;

import com.example.shop.member.Member;
import com.example.shop.member.repository.MemberRepository;
import com.example.shop.product.Product;
import com.example.shop.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Long createOrder(Map<String, Object> body) {

        Long memberId = ((Number) body.get("memberId")).longValue();
        Long productId = ((Number) body.get("productId")).longValue();

        Member member = memberRepository.findById(memberId);
        Product product = productRepository.findById(productId);

        Order order = new Order(
                member,
                product,
                ((Number) body.get("totalPrice")).intValue(),
                ((Number) body.get("pointUsed")).intValue(),
                ((Number) body.get("cashAmount")).intValue(),
                (String) body.get("status")
        );

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Order getOrderById(Long id) {
        Order order = orderRepository.findById(id);
        if (order == null) throw new RuntimeException("주문을 찾을 수 없습니다.");
        return order;
    }

    @Transactional
    @Override
    public void updateOrder(Long id, Map<String, Object> body) {
        Order order = orderRepository.findById(id);
        if (order == null) throw new RuntimeException("주문을 찾을 수 없습니다.");

        order.updateInfo(
                body.containsKey("totalPrice") ? ((Number) body.get("totalPrice")).intValue() : null,
                body.containsKey("pointUsed") ? ((Number) body.get("pointUsed")).intValue() : null,
                body.containsKey("cashAmount") ? ((Number) body.get("cashAmount")).intValue() : null,
                (String) body.get("status")
        );
    }

    @Transactional
    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
