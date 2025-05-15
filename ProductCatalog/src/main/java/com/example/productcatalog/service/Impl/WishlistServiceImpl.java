package com.example.productcatalog.service.Impl;

import com.example.productcatalog.dto.WishlistItemDTO;
import com.example.productcatalog.entity.Product;
import com.example.productcatalog.entity.WishlistItem;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.repository.WishlistRepository;
import com.example.productcatalog.service.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<WishlistItemDTO> getAllItems() {
        return wishlistRepository.findAll().stream()
                .map(item -> modelMapper.map(item, WishlistItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addItem(WishlistItemDTO dto) {
        // Fetch the Product entity by ID
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + dto.getProductId()));

        WishlistItem item = new WishlistItem();
        item.setProduct(product);

        wishlistRepository.save(item);
    }

    @Override
    public void removeItem(Long id) {
        WishlistItem item = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist item not found with id: " + id));
        wishlistRepository.delete(item);
    }
}
