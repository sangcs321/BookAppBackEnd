package com.example.demo.dto.response;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
public class OrderResponseDTO {
    private int id;
    private String orderCode;
    private String paymentMethod;
    private String shippingAddress;
    private String state;
    private Double totalPrice;
    private UserDTO user;
    private Timestamp createdAt;
    private List<OrderItemResponseDTO> orderItems;

    public static class OrderItemResponseDTO {
        private Integer id;
        private Integer productId;
        private String productName; // Thêm thông tin sản phẩm nếu cần
        private int quantity;
        private double productPrice;
        private double totalPrice;
        private String image;

        // Getters và setters
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public Integer getProductId() { return productId; }
        public void setProductId(Integer productId) { this.productId = productId; }

        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        public double getProductPrice() { return productPrice; }
        public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

        public double getTotalPrice() { return totalPrice; }
        public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }
    }
}
