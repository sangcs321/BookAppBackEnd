package com.example.demo.repository;

import com.example.demo.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @Query("SELECT c FROM CartItem c JOIN FETCH c.product p JOIN FETCH p.productImages pi JOIN FETCH pi.image WHERE c.user.id = :userId")
    List<CartItem> findByUserIdWithProductAndImages(int userId);
}
