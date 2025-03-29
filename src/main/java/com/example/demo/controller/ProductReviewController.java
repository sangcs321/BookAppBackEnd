package com.example.demo.controller;

import com.example.demo.Service.ProductReviewService;
import com.example.demo.entity.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ProductReviewController {
    @Autowired
    private ProductReviewService productReviewService;
    @GetMapping
    public List<ProductReview> getReviews() {
        return productReviewService.findAll();
    }
}
