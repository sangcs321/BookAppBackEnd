package com.example.demo.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationService {
    @Autowired
    private UserRepository userRepository;

    public void verifyEmail(String token) {
        Optional<UserEntity> user = userRepository.findByVerifiedToken(token);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Invalid token");
        }
        UserEntity userEntity = user.get();
        userEntity.setVerified(true);
        userEntity.setVerifiedToken(null);
        userRepository.save(userEntity);
    }
}
