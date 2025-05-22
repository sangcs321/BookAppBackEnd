package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "order_code")
    private String orderCode;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "payment_method")
    private String paymentMethod;
    private String state;

    public Order() {
    }

    public Order(int id, int userId, String orderCode, Timestamp createdAt, double totalPrice, String shippingAddress, String paymentMethod, String state) {
        this.id = id;
        this.userId = userId;
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.state = state;
    }

    public Order(int userId, String orderCode, Timestamp createdAt, double totalPrice, String shippingAddress, String paymentMethod, String state) {
        this.userId = userId;
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.state = state;
    }


}
