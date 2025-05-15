package com.example.productcatalog.controller;

import com.example.productcatalog.dto.CartItemDTO;
import com.example.productcatalog.service.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> getCartItemById(@PathVariable Long id) {
        return ResponseEntity.ok(cartItemService.getCartItemById(id));
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> addCartItem(@Valid @RequestBody CartItemDTO cartItemDTO) {
        CartItemDTO savedItem = cartItemService.addCartItem(cartItemDTO);
        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long id, @Valid @RequestBody CartItemDTO cartItemDTO) {
        CartItemDTO updatedItem = cartItemService.updateCartItem(id, cartItemDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
