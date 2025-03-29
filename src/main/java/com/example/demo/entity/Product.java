package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Arrays;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String category;
    private String body;
    private String status;
    private int discount;
    private int quantity;
    private double rating;
    private double price;
    private double priceImport;
    private String[] images;
    private int sold;

    public Product() {
    }

    public Product(int id, String title, String category, String body, String status, int discount, int quantity, double rating, double price, double priceImport, String[] images, int sold) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.body = body;
        this.status = status;
        this.discount = discount;
        this.quantity = quantity;
        this.rating = rating;
        this.price = price;
        this.priceImport = priceImport;
        this.images = images;
        this.sold = sold;
    }

    public Product(String title, String category, String body, String status, int discount, int quantity, double rating, double price, double priceImport, String[] images, int sold) {
        this.title = title;
        this.category = category;
        this.body = body;
        this.status = status;
        this.discount = discount;
        this.quantity = quantity;
        this.rating = rating;
        this.price = price;
        this.priceImport = priceImport;
        this.images = images;
        this.sold = sold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceImport() {
        return priceImport;
    }

    public void setPriceImport(double priceImport) {
        this.priceImport = priceImport;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", body='" + body + '\'' +
                ", status='" + status + '\'' +
                ", discount=" + discount +
                ", quantity=" + quantity +
                ", rating=" + rating +
                ", price=" + price +
                ", priceImport=" + priceImport +
                ", images=" + Arrays.toString(images) +
                ", sold=" + sold +
                '}';
    }
}
