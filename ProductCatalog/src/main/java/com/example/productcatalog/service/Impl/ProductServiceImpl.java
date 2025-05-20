package com.example.productcatalog.service.Impl;

import com.example.productcatalog.dto.FilterRequest;
import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.dto.SearchRequest;
import com.example.productcatalog.entity.Product;
import com.example.productcatalog.enums.SortOption;
import com.example.productcatalog.exception.ProductNotFoundException;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.service.ProductService;
import com.example.productcatalog.specification.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }


    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setCategory(productDTO.getCategory());
        existingProduct.setBrand(productDTO.getBrand());
        existingProduct.setColor(productDTO.getColor());
        existingProduct.setRating(productDTO.getRating());
        existingProduct.setImageUrl(productDTO.getImageUrl());

        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getProductsSorted(SortOption sortOption) {
        List<Product> productList = productRepository.findAll();

        if (sortOption != null) {
            switch (sortOption) {
                case PRICE_ASCENDING:
                    productList.sort(Comparator.comparing(Product::getPrice));
                    break;
                case PRICE_DESCENDING:
                    productList.sort(Comparator.comparing(Product::getPrice).reversed());
                    break;
                case RATING_ASCENDING:
                    productList.sort(Comparator.comparing(Product::getRating));
                    break;
                case RATING_DESCENDING:
                    productList.sort(Comparator.comparing(Product::getRating).reversed());
                    break;
                default:
                    productList.sort(Comparator.comparing(Product::getId));
            }
        }

        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductDTO dto = modelMapper.map(product, ProductDTO.class);
            dtoList.add(dto);
        }

        return dtoList;
    }
    private Sort getSortFromOption(SortOption sortOption) {
        if (sortOption == null) {
            return Sort.unsorted();
        }

        switch (sortOption) {
            case PRICE_ASCENDING:
                return Sort.by(Sort.Direction.ASC, "price");
            case PRICE_DESCENDING:
                return Sort.by(Sort.Direction.DESC, "price");
            case RATING_ASCENDING:
                return Sort.by(Sort.Direction.ASC, "rating");
            case RATING_DESCENDING:
                return Sort.by(Sort.Direction.DESC, "rating");
            default:
                return Sort.unsorted();
        }
    }



    @Override
    public List<ProductDTO> searchProducts(SearchRequest request) {
        String query = request.getQuery().trim().toLowerCase();
        Specification<Product> spec = ProductSpecification.searchByQuery(query);

        return productRepository.findAll(spec).stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }



    @Override
    public List<ProductDTO> filterProducts(FilterRequest filterRequest) {
        Specification<Product> spec = ProductSpecification.filterBy(filterRequest);

        SortOption sortOption;
        try {
            sortOption = SortOption.valueOf(filterRequest.getSortBy());
        } catch (IllegalArgumentException | NullPointerException e) {
            sortOption = null; // fallback if sortBy invalid or null
        }

        Sort sort = getSortFromOption(sortOption);

        List<Product> products = productRepository.findAll(spec, sort);

        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product : products) {
            dtoList.add(modelMapper.map(product, ProductDTO.class));
        }

        return dtoList;
    }

}

