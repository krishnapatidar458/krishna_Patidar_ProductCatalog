package com.example.productcatalog.service.impl;

import com.example.productcatalog.dto.CartItemDTO;
import com.example.productcatalog.entity.CartItem;
import com.example.productcatalog.entity.Product;
import com.example.productcatalog.repository.CartRepository;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.service.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CartItemDTO> getAllCartItems() {
        return cartRepository.findAll().stream()
                .map(cartItem -> {
                    CartItemDTO dto = modelMapper.map(cartItem, CartItemDTO.class);
                    dto.setProductId(cartItem.getProduct().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CartItemDTO getCartItemById(Long id) {
        CartItem cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        CartItemDTO dto = modelMapper.map(cartItem, CartItemDTO.class);
        dto.setProductId(cartItem.getProduct().getId());
        return dto;
    }

    @Override
    public CartItemDTO addCartItem(CartItemDTO cartItemDTO) {
        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        CartItem saved = cartRepository.save(cartItem);
        CartItemDTO savedDTO = modelMapper.map(saved, CartItemDTO.class);
        savedDTO.setProductId(saved.getProduct().getId());

        return savedDTO;
    }

    @Override
    public CartItemDTO updateCartItem(Long id, CartItemDTO cartItemDTO) {
        CartItem existingCartItem = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingCartItem.setProduct(product);
        existingCartItem.setQuantity(cartItemDTO.getQuantity());

        CartItem updated = cartRepository.save(existingCartItem);
        CartItemDTO updatedDTO = modelMapper.map(updated, CartItemDTO.class);
        updatedDTO.setProductId(updated.getProduct().getId());

        return updatedDTO;
    }

    @Override
    public void deleteCartItem(Long id) {
        CartItem cartItem = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        cartRepository.delete(cartItem);
    }
}
