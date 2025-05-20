package com.example.productcatalog.controller;

import com.example.productcatalog.dto.WishlistItemDTO;
import com.example.productcatalog.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<WishlistItemDTO>> getAllWishlistItems() {
        return ResponseEntity.ok(wishlistService.getAllItems());
    }

    @PostMapping
    public ResponseEntity<String> addItemToWishlist(@Valid @RequestBody WishlistItemDTO itemDTO) {
        wishlistService.addItem(itemDTO);
        return ResponseEntity.ok("Item added to wishlist successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemFromWishlist(@PathVariable Long id) {
        wishlistService.removeItem(id);
        return ResponseEntity.ok("Item removed from wishlist successfully.");
    }// i missed to show sry for that.
}
