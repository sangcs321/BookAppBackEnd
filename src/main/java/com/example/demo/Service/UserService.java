package com.example.demo.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder
    @Autowired
    private EmailService emailService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(username);
        if(user.isPresent()){
            var userOjbect = user.get();
            return User.builder()
                    .username(userOjbect.getEmail())
                    .password(userOjbect.getPassword())
                    .build();
        }else {
            throw new UsernameNotFoundException(username);
        }
    }

    public UserEntity registerUser(String email, String password, String username) throws MessagingException {
        // Kiểm tra xem email đã tồn tại chưa
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email đã được sử dụng: " + email);
        }

        // Tạo user mới
        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        newUser.setName(username);
        newUser.setRole(0);
        newUser.setVerified(false);
        // Gán role mặc định (nếu không lưu roles trong database)
        // Lưu user vào database
        emailService.sendVerificationEmail(newUser);
        return userRepository.save(newUser);
    }
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public UserDTO getUserById(Integer userId) {
        System.out.println("userService: "+userId);
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        UserEntity user = userOptional.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        System.out.println("userService: "+user.getId());
        return new UserDTO(user);
    }
}
