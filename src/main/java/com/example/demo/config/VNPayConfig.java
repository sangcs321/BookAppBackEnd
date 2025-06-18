package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Random;

public class VNPayConfig {
    public static String vnp_PayUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static String vnp_ReturnUrl = "http://localhost:8080/api/vnpay-return";
    public static String vnp_TmnCode = "PYL8MBSR";
    public static String vnp_HashSecret = "F5RY65V4TXDHLNHRV74WDI5BGHW3VNH4";

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        return ip != null ? ip : request.getRemoteAddr();
    }

    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
