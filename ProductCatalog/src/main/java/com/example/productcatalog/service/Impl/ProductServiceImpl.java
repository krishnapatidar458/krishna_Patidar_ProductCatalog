package com.example.productcatalog.service.Impl;

import com.example.productcatalog.dto.FilterRequest;
import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.dto.SearchRequest;
import com.example.productcatalog.entity.Product;
import com.example.productcatalog.enums.SortOption;
import com.example.productcatalog.exception.ProductNotFoundException;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (sortOption == null) {
            return productList.stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());
        }

        Comparator<Product> comparator;

        switch (sortOption) {
            case PRICE_ASCENDING:
                comparator = Comparator.comparing(Product::getPrice);
                break;
            case PRICE_DESCENDING:
                comparator = Comparator.comparing(Product::getPrice).reversed();
                break;
            case RATING_ASCENDING:
                comparator = Comparator.comparing(Product::getRating);
                break;
            case RATING_DESCENDING:
                comparator = Comparator.comparing(Product::getRating).reversed();
                break;
            default:
                comparator = Comparator.comparing(Product::getId);
        }

        return productList.stream()
                .sorted(comparator)
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchProducts(SearchRequest request) {
        String query = request.getQuery().toLowerCase();
        return productRepository.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(query) ||
                        product.getDescription().toLowerCase().contains(query) ||
                        product.getBrand().toLowerCase().contains(query) ||
                        product.getCategory().toLowerCase().contains(query))
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> filterProducts(FilterRequest filterRequest) {
        List<Product> products = productRepository.findAll();

        // Apply filters
        List<Product> filtered = products.stream()
                .filter(p -> filterRequest.getCategories() == null || filterRequest.getCategories().isEmpty() || filterRequest.getCategories().contains(p.getCategory()))
                .filter(p -> filterRequest.getBrands() == null || filterRequest.getBrands().isEmpty() || filterRequest.getBrands().contains(p.getBrand()))
                .filter(p -> filterRequest.getColors() == null || filterRequest.getColors().isEmpty() || filterRequest.getColors().contains(p.getColor()))
                .filter(p -> filterRequest.getMinPrice() == null || p.getPrice() >= filterRequest.getMinPrice())
                .filter(p -> filterRequest.getMaxPrice() == null || p.getPrice() <= filterRequest.getMaxPrice())
                .filter(p -> filterRequest.getMinRating() == null || p.getRating() >= filterRequest.getMinRating())
                .collect(Collectors.toList());

        // Apply sorting
        if (filterRequest.getSortBy() != null) {
            switch (filterRequest.getSortBy()) {
                case "PRICE_ASCENDING":
                    filtered.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "PRICE_DESCENDING":
                    filtered.sort(Comparator.comparing(Product::getPrice).reversed());
                    break;
                case "RATING_ASCENDING":
                    filtered.sort(Comparator.comparing(Product::getRating));
                    break;
                case "RATING_DESCENDING":
                    filtered.sort(Comparator.comparing(Product::getRating).reversed());
                    break;
                default:
                    break;
            }
        }

        // Convert to DTOs
        return filtered.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }


}
