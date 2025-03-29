package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String avatar;
    private String email;
    private String password;
    private int verified;
    private String verifiedToken;
    private int role;
    private int phone;
    private Timestamp createdAt;
    private int isBlock;

    public User(int id, String name, String avatar, String email, String password, int verified, String verifiedToken, int role, int phone, Timestamp createdAt, int isBlock) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.verifiedToken = verifiedToken;
        this.role = role;
        this.phone = phone;

        this.createdAt = createdAt;
        this.isBlock = isBlock;
    }

    public User(String name, String avatar, String email, String password, int verified, String verifiedToken, int role, int phone, Timestamp createdAt, int isBlock) {
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.verifiedToken = verifiedToken;
        this.role = role;
        this.phone = phone;
        this.createdAt = createdAt;
        this.isBlock = isBlock;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getVerifiedToken() {
        return verifiedToken;
    }

    public void setVerifiedToken(String verifiedToken) {
        this.verifiedToken = verifiedToken;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }
}
