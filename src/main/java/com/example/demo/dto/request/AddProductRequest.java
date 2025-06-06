package com.example.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddProductRequest {
    @NotBlank(message = "Title must not be blank")
    private String title;
    @NotBlank(message = "Category must not be blank")
    private String category;
    private String body;
    private String status;
    private String provider;
    private String author;
    private String publisher;
    @Min(value = 0, message = "Year of publication must be non-negative")
    private int yearPublic;
    private String language;
    private String weight;
    private String other;
    @Min(value = 0, message = "Discount must be non-negative")
    private int discount;
    @Min(value = 0, message = "Quantity must be non-negative")
    private int quantity;
    @Min(value = 0, message = "Price must be non-negative")
    private double price;
    @Min(value = 0, message = "Import price must be non-negative")
    private double priceImport;
    @Min(value = 0, message = "Sold quantity must be non-negative")
    private int sold;
    @NotNull(message = "Product images must not be null")
    private List<ProductImageRequest> productImages;
}
