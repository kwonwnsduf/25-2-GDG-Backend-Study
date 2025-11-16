package com.example.shop.order;

import java.util.List;

public interface OrderRepository {
    Order findById(Long id);
    List<Order> findAll();
    void save(Order order);
    void deleteById(Long id);
}
