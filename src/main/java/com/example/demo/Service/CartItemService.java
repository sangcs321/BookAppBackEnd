package com.example.demo.Service;

import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }
}
