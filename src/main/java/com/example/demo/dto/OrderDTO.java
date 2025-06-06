package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private int userId;
    private String orderCode;
    private Timestamp orderDate;
    private double totalPrice;
    private String shippingAddress;
    private String paymentMethod;
    private String state;
    private List<OrderItemDTO> orderItems;
    public OrderDTO() {
    }

    public OrderDTO(int userId, String orderCode, Timestamp orderDate, double totalPrice, String shippingAddress, String paymentMethod, String state) {
        this.userId = userId;
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.state = state;
    }
    @Getter
    @Setter
    public static class OrderItemDTO {
        private Integer productId;
        private int quantity;
        private double productPrice;
        // Getters và setters
    }
}
