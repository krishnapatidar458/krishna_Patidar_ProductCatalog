package com.example.productcatalog.repository;

import com.example.productcatalog.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {
    Optional<WishlistItem> findByProductId(Long productId);  // Find wishlist item by product ID
}
