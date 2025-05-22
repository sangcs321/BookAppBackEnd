package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_id")
    private int productId;
    @Column(name="order_id")
    private int orderId;
    private int quantity;
    @Column(name = "product_price")
    private double productPrice;
    @Column(name="order_price")
    private double orderPrice;

    public OrderItem(int productId, int orderId, int quantity, double productPrice, double orderPrice) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.orderPrice = orderPrice;
    }

    public OrderItem(int id, int productId, int orderId, int quantity, double productPrice, double orderPrice) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.orderPrice = orderPrice;
    }

    public OrderItem() {
    }


}
