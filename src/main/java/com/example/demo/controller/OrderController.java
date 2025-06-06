package com.example.demo.controller;

import com.example.demo.Service.OrderService;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.request.OrderRequestDTO;
import com.example.demo.dto.response.OrderResponseDTO;
import com.example.demo.entity.Orders;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public List<OrderResponseDTO> getOrders() {
        return orderService.findAll();
    }
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO request) {
        System.out.println(request);
        OrderResponseDTO response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}/state")
    public ResponseEntity<OrderResponseDTO> updateStateOrder(@PathVariable("id") String id, @Param(value = "state") String state) {
        System.out.println(state);
        int orderId = Integer.parseInt(id);
        OrderResponseDTO updatedOrder = orderService.updateState(orderId, state);
        return new ResponseEntity<OrderResponseDTO>(updatedOrder, HttpStatus.OK);
    }
}
