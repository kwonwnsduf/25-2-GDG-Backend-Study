package com.example.shop.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList();
    }

    /** 예: 주문번호(orderNumber)로 단건 조회 (필드명에 맞게 사용) */
    public Order findByOrderNumber(String orderNumber) {
        List<Order> result = em.createQuery(
                        "SELECT o FROM Order o WHERE o.orderNumber = :orderNumber",
                        Order.class)
                .setParameter("orderNumber", orderNumber)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /** 주문 생성 */
    public void save(Order order) {
        em.persist(order);
    }

    /** 주문 삭제(물리 삭제가 필요할 때). 보통은 상태값을 'CANCELED'로 변경 */
    public void deleteById(Long id) {
        Order order = em.find(Order.class, id);
        if (order != null) em.remove(order);
    }
}

