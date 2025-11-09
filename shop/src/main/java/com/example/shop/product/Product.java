package com.example.shop.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", length = 100, nullable = false)
    private String name;

    @Column(name = "product_price", nullable = false)
    private int price;

    @Column(name = "product_stock", nullable = false)
    private int stock;

    @Column(name = "product_description", length = 1000)
    private String description;

    @Column(name = "product_status", length = 20)
    private String status; // e.g. "ON_SALE", "SOLD_OUT"

    public Product(String name, int price, int stock, String description, String status) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.status = status;
    }

    public void update(String name, int price, int stock, String description, String status) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.status = status;
    }

    public void decreaseStock(int quantity) {
        if (this.stock < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.stock -= quantity;
    }

    public void increaseStock(int quantity) {
        this.stock += quantity;
    }
}

