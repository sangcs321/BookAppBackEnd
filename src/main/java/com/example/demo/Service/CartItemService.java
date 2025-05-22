package com.example.demo.Service;

import com.example.demo.dto.CartItemDTO;
import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductImageDTO;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    //    public List<CartItem> findAll() {
//        return cartItemRepository.findAll();
//    }
    // Thêm hoặc cập nhật CartItem
    public CartItemDTO addCartItem(CartItem cartItem) {
        if (cartItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        // Kiểm tra xem sản phẩm đã có trong giỏ của user chưa
        List<CartItem> existingItems = cartItemRepository.findByUserIdWithProductAndImages(cartItem.getUser().getId());
        for (CartItem item : existingItems) {
            if (item.getProduct().getId() == cartItem.getProduct().getId()) {
                item.setQuantity(item.getQuantity()+1);
                return convertToCartItemDTO(cartItemRepository.save(item));
            }
        }
        return convertToCartItemDTO(cartItemRepository.save(cartItem));
    }

    // Xóa CartItem
    public void deleteCartItem(int id) {
        if (!cartItemRepository.existsById(id)) {
            throw new RuntimeException("CartItem not found with id: " + id);
        }
        cartItemRepository.deleteById(id);
    }

    // Sửa số lượng CartItem
    public CartItemDTO updateQuantity(int id, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found with id: " + id));
        cartItem.setQuantity(quantity);
        return convertToCartItemDTO(cartItemRepository.save(cartItem));
    }

    public List<CartItemDTO> getCartItemsByUserId(int userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserIdWithProductAndImages(userId);
        return cartItems.stream().map(this::convertToCartItemDTO).collect(Collectors.toList());
    }
    private CartItemDTO convertToCartItemDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setProduct(convertToProductDTO(cartItem.getProduct()));
        return cartItemDTO;
    }
    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());

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
