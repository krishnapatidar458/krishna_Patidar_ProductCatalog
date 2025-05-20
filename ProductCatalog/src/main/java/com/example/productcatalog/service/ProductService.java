package com.example.productcatalog.service;

import com.example.productcatalog.dto.FilterRequest;
import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.dto.SearchRequest;
import com.example.productcatalog.enums.SortOption;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);

    List<ProductDTO> getProductsSorted(SortOption sortOption);
    List<ProductDTO> searchProducts(SearchRequest request);

    List<ProductDTO> filterProducts(FilterRequest filterRequest);

}
