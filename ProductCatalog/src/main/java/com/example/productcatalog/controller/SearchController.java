package com.example.productcatalog.controller;

import com.example.productcatalog.dto.SearchRequest;
import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<List<ProductDTO>> searchProducts(@Valid @RequestBody SearchRequest request) {
        List<ProductDTO> results = productService.searchProducts(request);
        return ResponseEntity.ok(results);
    }
}
