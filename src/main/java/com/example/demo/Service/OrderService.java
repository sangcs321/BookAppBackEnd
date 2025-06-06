package com.example.demo.Service;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.request.OrderRequestDTO;
import com.example.demo.dto.response.OrderResponseDTO;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    public List<OrderResponseDTO> findAll() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {        // Tạo mã đơn hàng ngẫu nhiên
        String orderCode = "ORDER-" + UUID.randomUUID().toString().substring(0, 8);
        // Kiểm tra trùng mã đơn hàng
        while (orderRepository.findByOrderCode(orderCode) != null) {
            orderCode = "ORDER-" + UUID.randomUUID().toString().substring(0, 8);
        }
        Orders orders = new Orders();
        orders.setCreatedAt(new Timestamp(System.currentTimeMillis())); // Thời gian hiện tại
        orders.setOrderCode(orderCode);
        orders.setPaymentMethod(request.getPaymentMethod());
        orders.setShippingAddress(request.getShippingAddress());
        orders.setState(request.getState());
        orders.setTotalPrice(request.getTotalPrice());
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getUserId()));
        orders.setUser(user); // Set User thay vì userId

        for (OrderRequestDTO.OrderItemDTO itemDTO : request.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + itemDTO.getProductId()));
            OrderItem item = new OrderItem(orders, product, itemDTO.getQuantity(), itemDTO.getProductPrice());
            orders.addOrderItem(item);
        }
        // Lưu vào database
        Orders savedOrder = orderRepository.save(orders);
        return mapToResponseDTO(savedOrder);
    }
    @Transactional
    public OrderResponseDTO updateState(int id, String state) {
        if (state == null || state.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "State cannot be empty");
        }
        // Tìm order
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
        order.setState(state);
        Orders updatedOrder = orderRepository.save(order);
        return mapToResponseDTO(updatedOrder);
    }
    private OrderResponseDTO mapToResponseDTO(Orders order) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId());
        response.setOrderCode(order.getOrderCode());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setShippingAddress(order.getShippingAddress());
        response.setState(order.getState());
        response.setTotalPrice(order.getTotalPrice());
        response.setUser(new UserDTO(order.getUser()));
        response.setCreatedAt(order.getCreatedAt());

        List<OrderResponseDTO.OrderItemResponseDTO> itemDTOs = order.getOrderItems().stream()
                .map(item -> {
                    OrderResponseDTO.OrderItemResponseDTO itemDTO = new OrderResponseDTO.OrderItemResponseDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setProductId(item.getProduct().getId());
                    itemDTO.setProductName(item.getProduct().getTitle());
                    itemDTO.setQuantity(item.getQuantity());
                    itemDTO.setProductPrice(item.getProductPrice());
                    itemDTO.setTotalPrice(item.getOrder().getTotalPrice());
                    itemDTO.setImage(item.getProduct().getProductImages().get(0).getImage().getUrl());
                    return itemDTO;
                })
                .collect(Collectors.toList());
        response.setOrderItems(itemDTOs);

        return response;
    }
}
