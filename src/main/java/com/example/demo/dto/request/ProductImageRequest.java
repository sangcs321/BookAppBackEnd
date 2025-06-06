package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageRequest {
    @NotBlank(message = "Image URL must not be blank")
    private String url;

    @NotBlank(message = "Public ID must not be blank")
    private String publicId;
}
