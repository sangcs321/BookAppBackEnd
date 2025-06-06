package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "image_id")
    private Integer id_image;
    private String email;
    private String password;
    @Column(columnDefinition = "TINYINT(1)")
    private boolean verified;
    @Column(name = "verified_token") // Thêm field verifiedToken
    private String verifiedToken;
    private int role;
    private String phone;
    private String address;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "is_block")
    private boolean isBlock;
    @OneToMany(mappedBy = "user")
    private List<CartItem> cartItems;

    public UserEntity(int id, String name, Integer id_image, String email, String password, boolean verified, String verifiedToken, int role, String phone,String address, Timestamp createdAt, boolean isBlock) {
        this.id = id;
        this.name = name;
        this.id_image = id_image;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.verifiedToken = verifiedToken;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.isBlock = isBlock;
    }

    public UserEntity() {
    }

    public UserEntity(String name, Integer id_image, String email, String password, boolean verified, String verifiedToken, int role, String phone,String address, Timestamp createdAt, boolean isBlock) {
        this.name = name;
        this.id_image = id_image;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.verifiedToken = verifiedToken;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.isBlock = isBlock;
    }
}
