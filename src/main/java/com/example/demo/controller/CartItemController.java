package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cartItems")
public class CartItemController {
    @Autowired
    private CartItemRepository cartItemRepository;
    @GetMapping
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }
}
