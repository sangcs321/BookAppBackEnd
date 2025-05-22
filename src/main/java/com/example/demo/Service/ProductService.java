package com.example.demo.Service;

import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductImageDTO;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //    public List<Product> findAll() {
//        return productRepository.findAll();
//    }
    public List<ProductDTO> getAllProductsWithImages() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setSold(product.getSold());

        List<ProductImageDTO> productImageDTOs = product.getProductImages().stream()
                .map(this::convertToProductImageDTO)
                .collect(Collectors.toList());
        productDTO.setProductImages(productImageDTOs);

        return productDTO;
    }
    private ProductImageDTO convertToProductImageDTO(ProductImage productImage) {
        ProductImageDTO productImageDTO = new ProductImageDTO();
        productImageDTO.setId(productImage.getId());
        productImageDTO.setImage(convertToImageDTO(productImage.getImage()));
        return productImageDTO;
    }

    private ImageDTO convertToImageDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        imageDTO.setPublicId(image.getPublicId());
        return imageDTO;
    }
}
