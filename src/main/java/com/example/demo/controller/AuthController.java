package com.example.demo.controller;

import com.example.demo.Security.JwtTokenProvider;
import com.example.demo.Service.UserService;
import com.example.demo.Service.VerificationService;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController // Sửa từ @Configuration thành @RestController
@RequestMapping("/api/auth")
public class AuthController { // Đổi tên thành AuthController để phù hợp với chức năng

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    private VerificationService verificationService;
    // Inject qua constructor thay vì @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email,
                                   @RequestParam("password") String password) {
        // Kiểm tra email và password không rỗng
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email và mật khẩu không được để trống");
        }

        try {
            // Xác thực người dùng
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // Set authentication vào SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Lấy thông tin
            Optional<UserEntity> userOptional = userRepository.findByEmail(email);
            UserEntity user = userOptional.orElseThrow(() -> new RuntimeException("User not found with email: " + email));
            UserDetails userDetails = userService.loadUserByUsername(email);// Giả sử UserService có phương thức findByEmail
            // Tạo JWT token
            String jwtToken = jwtTokenProvider.generateToken(userDetails);

            // Tạo response
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("userId", String.valueOf(user.getId())); // Bây giờ có thể gọi getId()
            response.put("message", "Đăng nhập thành công");

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Email hoặc mật khẩu không đúng");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã có lỗi xảy ra trong quá trình đăng nhập: " + e.getMessage());
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @ModelAttribute RegisterRequest request) {
        try {
            // Gọi UserService để đăng ký user mới
            UserEntity newUser = userService.registerUser(
                    request.getEmail(),
                    request.getPassword(),
                    request.getUsername()
            );
            Map<String, String> response = new HashMap<>();
            response.put("message", "Đăng ký thành công cho email: " + newUser.getEmail());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Đã có lỗi xảy ra trong quá trình đăng ký: " + e.getMessage());
        }
    }
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        verificationService.verifyEmail(token);
        return ResponseEntity.ok("Email verified successfully");
    }
}