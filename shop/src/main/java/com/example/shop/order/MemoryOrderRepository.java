package com.example.shop.order;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("subOrderRepository")
public class MemoryOrderRepository implements OrderRepository {

    private final Map<Long, Order> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Order findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void save(Order order) {
        if (order.getId() == null) {
            sequence++;
            order.setId(sequence);
        }
        store.put(order.getId(), order);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
