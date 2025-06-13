package com.example.demo.controller;

import com.example.demo.Service.ProductService;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.request.AddProductRequest;
import com.example.demo.dto.response.CheckQuantityResponse;
import com.example.demo.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{id}/update-quantity")
    public ResponseEntity<ProductDTO> updateProductQuantity(
            @PathVariable String id,
            @RequestParam("quantity") int quantity) {
//        Integer quantity = Integer.parseInt(String.valueOf(request));
        try {
            Integer productId = Integer.parseInt(id);
            ProductDTO updatedProduct = productService.updateProductQuantity(productId, quantity);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/{id}/check-quantity")
    public ResponseEntity<CheckQuantityResponse> checkProductQuantity(
            @PathVariable int id,
            @RequestParam("quantity") int quantity) {
        try {
            CheckQuantityResponse response = productService.checkProductQuantity(id, quantity);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            CheckQuantityResponse errorResponse = new CheckQuantityResponse(
                    false, 0, "Error: " + e.getMessage()
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @GetMapping("/search")
    public List<ProductDTO> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category) {
        List<ProductDTO> products = productService.searchProducts(keyword, category);
        return products;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody AddProductRequest request) {
        try {
            ProductDTO productDTO = productService.addProduct(request);
            return ResponseEntity.ok(productDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
