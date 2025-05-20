package com.example.productcatalog.controller;

import com.example.productcatalog.dto.FilterRequest;
import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.dto.SearchRequest;
import com.example.productcatalog.enums.SortOption;
import com.example.productcatalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct( @RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public List<ProductDTO> getSortedProducts(@RequestParam SortOption sortOption) {
        return productService.getProductsSorted(sortOption);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProductDTO>> filterProducts(@RequestBody FilterRequest filterRequest) {
        List<ProductDTO> filtered = productService.filterProducts(filterRequest);
        return ResponseEntity.ok(filtered);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@Valid @RequestBody SearchRequest request) {
        List<ProductDTO> results = productService.searchProducts(request);
        return ResponseEntity.ok(results);
    }
}
