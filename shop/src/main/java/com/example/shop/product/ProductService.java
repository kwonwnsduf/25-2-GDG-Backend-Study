package com.example.shop.product;

import com.example.shop.product.Product;

import java.util.List;

public interface ProductService {
    Long createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}