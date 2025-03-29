package com.example.demo.Service;
import com.example.demo.entity.ProductReview;
import com.example.demo.repository.ProductReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;
    public List<ProductReview> findAll() {
        return productReviewRepository.findAll();
    }
}
