package com.example.productcatalog.service;

import com.example.productcatalog.dto.WishlistItemDTO;

import java.util.List;

public interface WishlistService {
    List<WishlistItemDTO> getAllItems();
    void addItem(WishlistItemDTO itemDTO);
    void removeItem(Long id);
}
