package com.example.demo.controller;

import com.example.demo.Service.OrderService;
import com.example.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAll();
    }
}
