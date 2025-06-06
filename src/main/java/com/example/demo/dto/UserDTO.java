package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private int id;
    private String name;
    private Integer id_image;
    private String email;
    private String phone;
    private boolean verified;
    private String address;
    private int role;
    private boolean isBlock;

    public UserDTO() {}
    public UserDTO(int id, String name,Integer id_image, String email, String phone, boolean verified, String address, int role, boolean isBlock) {
        this.id = id;
        this.name = name;
        this.id_image = id_image;
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
        this.id_image = user.getId_image();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.isBlock = user.isBlock();
        this.verified = user.isVerified();
    }




}
