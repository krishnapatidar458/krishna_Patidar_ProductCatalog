package com.example.productcatalog.repository;

import com.example.productcatalog.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProductId(Long productId);
    void deleteByProductId(Long productId);
}
