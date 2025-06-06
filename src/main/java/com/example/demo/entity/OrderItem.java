package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @Column(name = "product_price")
    @Positive(message = "Product price must be positive")
    private double productPrice;
    public OrderItem() {}

    public OrderItem(Orders order, Product product, int quantity, double productPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }
}
