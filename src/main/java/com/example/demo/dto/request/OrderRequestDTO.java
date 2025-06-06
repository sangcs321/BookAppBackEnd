package com.example.demo.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequestDTO {
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;

    @NotBlank(message = "State is required")
    private String state;

    private double totalPrice;

    @NotNull(message = "User ID is required")
    private int userId;

    @NotEmpty(message = "Order items cannot be empty")
    @Valid
    private List<OrderItemDTO> orderItems;

    public static class OrderItemDTO {
        @NotNull(message = "Product ID is required")
        private Integer productId;

        @Min(value = 1, message = "Quantity must be at least 1")
        private int quantity;

        @Positive(message = "Product price must be positive")
        private double productPrice;

        // Getters và setters
        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }
    }
}
