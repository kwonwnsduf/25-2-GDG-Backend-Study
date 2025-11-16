package com.example.shop.order;

import com.example.shop.member.Member;
import com.example.shop.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    // 주문한 회원
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 주문한 상품 (단순하게 1개의 상품만 주문했다고 가정)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "point_used")
    private int pointUsed;

    @Column(name = "cash_amount")
    private int cashAmount;

    @Column(name = "status", length = 25)
    private String status;

    public Order(Member member,
                 Product product,
                 int totalPrice,
                 int pointUsed,
                 int cashAmount,
                 String status) {
        this.member = member;
        this.product = product;
        this.totalPrice = totalPrice;
        this.pointUsed = pointUsed;
        this.cashAmount = cashAmount;
        this.status = status;
        this.orderDate = LocalDateTime.now();
    }

    public void updateStatus(String status) {
        this.status = status;
    }
    public void updateInfo(Integer totalPrice, Integer pointUsed, Integer cashAmount, String status) {
        if (totalPrice != null) this.totalPrice = totalPrice;
        if (pointUsed != null) this.pointUsed = pointUsed;
        if (cashAmount != null) this.cashAmount = cashAmount;
        if (status != null) this.status = status;
    }
}
