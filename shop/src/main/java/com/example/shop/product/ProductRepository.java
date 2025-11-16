package com.example.shop.product;

import com.example.shop.product.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    void save(Product product);
    void deleteById(Long id);
}