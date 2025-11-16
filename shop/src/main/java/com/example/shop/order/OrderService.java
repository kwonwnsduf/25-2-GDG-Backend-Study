package com.example.shop.order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Long createOrder(Map<String, Object> body);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void updateOrder(Long id, Map<String, Object> body);
    void deleteOrder(Long id);
}