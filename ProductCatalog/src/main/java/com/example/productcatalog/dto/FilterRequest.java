package com.example.productcatalog.dto;

import lombok.Data;
import java.util.List;

@Data
public class FilterRequest {
    private List<String> categories;
    private List<String> brands;
    private List<String> colors;
    private Double minPrice;
    private Double maxPrice;
    private Double minRating;
    private String sortBy;
}
