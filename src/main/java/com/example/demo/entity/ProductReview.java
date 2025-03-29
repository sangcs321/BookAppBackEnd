package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.repository.cdi.Eager;

import java.sql.Timestamp;
import java.util.Arrays;

@Entity
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int userId;
    private String orderCode;
    private double rating;
    private Timestamp createdAt;
    private String review;
    private String[] images;

    public ProductReview() {
    }

    public ProductReview(int id, int productId, int userId, String orderCode, double rating, Timestamp createdAt, String review, String[] images) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.orderCode = orderCode;
        this.rating = rating;
        this.createdAt = createdAt;
        this.review = review;
        this.images = images;
    }

    public ProductReview(int productId, int userId, String orderCode, double rating, Timestamp createdAt, String review, String[] images) {
        this.productId = productId;
        this.userId = userId;
        this.orderCode = orderCode;
        this.rating = rating;
        this.createdAt = createdAt;
        this.review = review;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", productId=" + productId +
                ", userId=" + userId +
                ", orderCode='" + orderCode + '\'' +
                ", rating=" + rating +
                ", createdAt=" + createdAt +
                ", review='" + review + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
