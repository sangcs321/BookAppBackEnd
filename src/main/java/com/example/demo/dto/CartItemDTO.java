package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemDTO {
    private int id;
    private int quantity;
    private ProductDTO product;
}
