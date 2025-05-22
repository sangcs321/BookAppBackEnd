package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p " +
            "LEFT JOIN FETCH p.productImages pi " +
            "LEFT JOIN FETCH pi.image " +
            "WHERE p.id = :productId")
    Product findProductWithImages(@Param("productId") int productId);
    // Phương thức mới để lấy tất cả Product
    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN FETCH p.productImages pi " +
            "LEFT JOIN FETCH pi.image")
    List<Product> findAllProductsWithImages();
}
