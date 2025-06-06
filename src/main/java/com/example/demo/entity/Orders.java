package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders") // Đảm bảo khai báo đúng
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Khóa ngoại tới bảng users
    private UserEntity user; // Thay userId bằng entity User
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    public Orders() {
    }

    public Orders(int id, UserEntity user, String orderCode, Timestamp createdAt, double totalPrice, String shippingAddress, String paymentMethod, String state) {
        this.id = id;
        this.user = user;
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.state = state;
    }

    public Orders(UserEntity user, String orderCode, Timestamp createdAt, double totalPrice, String shippingAddress, String paymentMethod, String state) {
        this.user = user;
        this.orderCode = orderCode;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.state = state;
    }
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setOrder(this);
    }

}
