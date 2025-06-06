package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages=new ArrayList<>();
    private String title;
    private String category;
    private String body;
    private String status;
    private String provider;
    private String author;
    private String publisher;
    @Column(name = "year_public")
    private int yearPublic;
    private String language;
    private String weight;
    private String other;
    private int discount;
    private int quantity;
    private double rating;
    private double price;
    @Column(name = "price_import")
    private double priceImport;
    private int sold;

    public Product() {
    }

    public Product(int id, String title, String category, String body, String status, String provider, String author, String publisher, int yearPublic, String language, String weight, String other, int discount, int quantity, double rating, double price, double priceImport, int sold) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.body = body;
        this.status = status;
        this.provider = provider;
        this.author = author;
        this.publisher = publisher;
        this.yearPublic = yearPublic;
        this.language = language;
        this.weight = weight;
        this.other = other;
        this.discount = discount;
        this.quantity = quantity;
        this.rating = rating;
        this.price = price;
        this.priceImport = priceImport;
        this.sold = sold;
    }

    public Product(String title, String category, String body, String status, String provider, String author, String publisher, int yearPublic, String language, String weight, String other, int discount, int quantity, double rating, double price, double priceImport, int sold) {
        this.title = title;
        this.category = category;
        this.body = body;
        this.status = status;
        this.provider = provider;
        this.author = author;
        this.publisher = publisher;
        this.yearPublic = yearPublic;
        this.language = language;
        this.weight = weight;
        this.other = other;
        this.discount = discount;
        this.quantity = quantity;
        this.rating = rating;
        this.price = price;
        this.priceImport = priceImport;
        this.sold = sold;
    }


}
