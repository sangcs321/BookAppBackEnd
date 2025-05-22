package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import java.sql.Timestamp;
import java.util.Arrays;
@Getter
@Setter
@Entity
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "user_id")
    private int userId;
    private String orderCode;
    private double rating;
    @Column(name = "created_at")
    private Timestamp createdAt;
    private String review;


    public ProductReview() {
    }

    public ProductReview(int id, int productId, int userId, String orderCode, double rating, Timestamp createdAt, String review) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.orderCode = orderCode;
        this.rating = rating;
        this.createdAt = createdAt;
        this.review = review;
    }

    public ProductReview(int productId, int userId, String orderCode, double rating, Timestamp createdAt, String review) {
        this.productId = productId;
        this.userId = userId;
        this.orderCode = orderCode;
        this.rating = rating;
        this.createdAt = createdAt;
        this.review = review;
    }
}
