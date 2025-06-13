package com.example.demo.Service;

import com.example.demo.dto.ImageDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductImageDTO;
import com.example.demo.dto.request.AddProductRequest;
import com.example.demo.dto.request.ProductImageRequest;
import com.example.demo.dto.response.CheckQuantityResponse;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ProductImageRepository;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ImageRepository imageRepository;


    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);



    public List<ProductDTO> getAllProductsWithImages() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }


    public ProductDTO updateProductQuantity(int productId,int quantity) {
        Product product = productRepository.findById(productId).get();
        int newQuantity = product.getQuantity() - quantity;
        int newsold = product.getSold() + quantity;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative. Current quantity: " + product.getQuantity());
        }
        product.setQuantity(newQuantity);
        product.setSold(newsold);
        productRepository.save(product); // Lưu entity Product
        ProductDTO productDTO = convertToProductDTO(product);
        return productDTO;
    }

    public ProductDTO addProduct(AddProductRequest request) {
        logger.info("Adding new product: {}", request.getTitle());

        // Tạo sản phẩm mới
        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setCategory(request.getCategory());
        product.setBody(request.getBody());
        product.setStatus(request.getStatus());
        product.setProvider(request.getProvider());
        product.setAuthor(request.getAuthor());
        product.setPublisher(request.getPublisher());
        product.setYearPublic(request.getYearPublic());
        product.setLanguage(request.getLanguage());
        product.setWeight(request.getWeight());
        product.setOther(request.getOther());
        product.setDiscount(request.getDiscount());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setPriceImport(request.getPriceImport());
        product.setSold(request.getSold());

        // Lưu sản phẩm
        Product savedProduct = productRepository.save(product);
        logger.info("Product saved with ID: {}", savedProduct.getId());

        // Xử lý hình ảnh
        List<ProductImageDTO> productImageDTOs = new ArrayList<>();
        for (ProductImageRequest imageRequest : request.getProductImages()) {
            // Tạo bản ghi Image
            Image image = new Image();
            image.setUrl(imageRequest.getUrl());
            image.setPublicId(imageRequest.getPublicId());
            Image savedImage = imageRepository.save(image);
            logger.info("Image saved with ID: {}", savedImage.getId());

            // Tạo bản ghi ProductImage
            ProductImage productImage = new ProductImage();
            productImage.setProduct(savedProduct);
            productImage.setImage(savedImage);
            ProductImage savedProductImage = productImageRepository.save(productImage);
            logger.info("ProductImage saved with ID: {}", savedProductImage.getId());

            // Tạo ImageDTO
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setId(savedImage.getId());
            imageDTO.setUrl(savedImage.getUrl());
            imageDTO.setPublicId(savedImage.getPublicId());

            // Tạo ProductImageDTO
            ProductImageDTO productImageDTO = new ProductImageDTO();
            productImageDTO.setId(savedProductImage.getId());
            productImageDTO.setImage(imageDTO); // Gán ImageDTO vào ProductImageDTO
            productImageDTOs.add(productImageDTO);
        }

        // Chuyển đổi thành DTO và trả về
        ProductDTO productDTO = convertToProductDTO(savedProduct);
        productDTO.setProductImages(productImageDTOs);
        return productDTO;
    }
    public List<ProductDTO> searchProducts(String keyword, String category) {
        List<Product> products = productRepository.findByTitleContainingIgnoreCaseAndCategory(
                keyword != null ? keyword.toLowerCase() : null,
                category != null ? category.toLowerCase() : null
        );
        return products.stream()
                .map(this::convertToProductDTO).collect(Collectors.toList());
    }



    public CheckQuantityResponse checkProductQuantity(int productId, int quantity) {
        logger.info("Checking quantity for product ID: {}, required quantity: {}", productId, quantity);

        // Tìm sản phẩm theo ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    logger.warn("Product with ID {} not found", productId);
                    return new IllegalArgumentException("Product with ID " + productId + " not found");
                });

        // Kiểm tra số lượng
        int currentQuantity = product.getQuantity();
        if (quantity < 0) {
            logger.warn("Invalid quantity requested for product ID: {}. Quantity must be non-negative: {}", productId, quantity);
            throw new IllegalArgumentException("Quantity must be non-negative");
        }

        boolean available = currentQuantity >= quantity;
        String message = available
                ? "Số lượng đủ"
                : "Số lượng không đủ. Cần " + quantity + ", nhưng chỉ còn " + currentQuantity + ".";

        logger.info("Product ID: {}, available: {}, current quantity: {}, message: {}", productId, available, currentQuantity, message);
        return new CheckQuantityResponse(available, currentQuantity, message);
    }
    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setCategory(product.getCategory());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setSold(product.getSold());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setBody(product.getBody());
        productDTO.setLanguage(product.getLanguage());
        productDTO.setPublisher(product.getPublisher());
        productDTO.setYearPublic(product.getYearPublic());
        productDTO.setAuthor(product.getAuthor());
        productDTO.setProvider(product.getProvider());
        productDTO.setOther(product.getOther());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setStatus(product.getStatus());
        logger.info("Product images: {}", product.getProductImages());
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
