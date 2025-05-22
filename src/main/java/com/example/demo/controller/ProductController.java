package com.example.demo.controller;

import com.example.demo.Service.ProductService;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public List<ProductDTO> getAllProductsWithImages() {
        return productService.getAllProductsWithImages();
    }
}
