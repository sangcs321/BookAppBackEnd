package com.example.demo.Service;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    public void sendVerificationEmail(UserEntity user) throws MessagingException {
        // Tạo token duy nhất
        String token = UUID.randomUUID().toString();

        // Lưu token và thời gian hết hạn vào user
        user.setVerifiedToken(token);
        userRepository.save(user);

        // Tạo link xác nhận
        String link = "http://localhost:8080/api/auth/verify?token=" + token;

        // Tạo nội dung email
        String subject = "Verify Your Email";
        String content = "<h3>Welcome, " + user.getName() + "!</h3>" +
                "<p>Please click the link below to verify your email:</p>" +
                "<a href=\"" + link + "\">Verify Email</a>" +
                "<p>This link will expire in 24 hours.</p>";

        // Gửi email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(content, true); // true để hỗ trợ HTML
        mailSender.send(message);
    }
}

