package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private int id;
    private String title;
    private String category;
    private String body;
    private String status;
    private String provider;
    private String author;
    private String publisher;
    private int yearPublic;
    private String language;
    private String weight;
    private String other;
    private int discount;
    private int quantity;
    private double rating;
    private double price;
    private double priceImport;
    private int sold;
    private List<ProductImageDTO> productImages;
}
