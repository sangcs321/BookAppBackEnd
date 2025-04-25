package com.example.demo.dto;

import com.example.demo.entity.UserEntity;

public class UserDTO {
    private int id;
    private String name;
    private String avatar;
    private String email;
    private String phone;
    private boolean verified;
    private String address;
    private int role;
    private int isBlock;

    public UserDTO(int id, String name, String avatar, String email, String phone, boolean verified, String address, int role, int isBlock) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.verified = verified;
        this.address = address;
        this.role = role;
        this.isBlock = isBlock;
    }

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.phone = user.getPhone();
        this.isBlock = user.getIsBlock();
        this.verified = user.getVerified();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int isBlock() {
        return isBlock;
    }

    public void setBlock(int block) {
        isBlock = block;
    }
}
