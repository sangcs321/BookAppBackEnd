package com.example.demo.controller;

import com.example.demo.Service.CartItemService;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @GetMapping("/user/{userId}")
    public List<CartItemDTO> getCartItemsByUserId(@PathVariable int userId) {
        return cartItemService.getCartItemsByUserId(userId);
    }
    // Thêm sản phẩm vào giỏ hàng
    @PostMapping
    public ResponseEntity<CartItemDTO> addCartItem(@RequestBody CartItem cartItem) {
        CartItemDTO savedItem = cartItemService.addCartItem(cartItem);
        return ResponseEntity.ok(savedItem);
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable int id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }

    // Sửa số lượng sản phẩm
    @PutMapping("/{id}/{quantity}")
    public ResponseEntity<CartItemDTO> updateQuantity(@PathVariable int id, @PathVariable int quantity) {
        CartItemDTO updatedItem = cartItemService.updateQuantity(id, quantity);
        return ResponseEntity.ok(updatedItem);
    }
}
