package com.example.productcatalog.service;

import com.example.productcatalog.dto.CartItemDTO;

import java.util.List;

public interface CartItemService {
    List<CartItemDTO> getAllCartItems();
    CartItemDTO getCartItemById(Long id);
    CartItemDTO addCartItem(CartItemDTO cartItemDTO);
    CartItemDTO updateCartItem(Long id, CartItemDTO cartItemDTO);
    void deleteCartItem(Long id);
}
