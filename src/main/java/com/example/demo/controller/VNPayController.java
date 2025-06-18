package com.example.demo.controller;

import com.example.demo.Service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VNPayController {
    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/vnpay/create-payment")
    public ResponseEntity<String> createPayment(HttpServletRequest request,
                                                @RequestParam(value = "amount", required = true, defaultValue = "0") String amount,
                                                @RequestParam("orderInfo") String orderInfo,
                                                @RequestParam("returnUrl") String returnUrl) {
        System.out.println("Received amount: " + amount);
        System.out.println("Received orderInfo: " + orderInfo);
        System.out.println("Received returnUrl: " + returnUrl);
        int amount_int = Integer.parseInt(amount);
        String paymentUrl = vnPayService.createPaymentUrl(request, amount_int, orderInfo, returnUrl);
        return ResponseEntity.ok(paymentUrl);
    }

    @GetMapping("/vnpay-return")
    public ResponseEntity<Map<String, String>> paymentReturn(HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        response.put("vnp_ResponseCode", vnp_ResponseCode);
        response.put("message", "00".equals(vnp_ResponseCode) ? "Payment successful" : "Payment failed");
        return ResponseEntity.ok(response);
    }
}
