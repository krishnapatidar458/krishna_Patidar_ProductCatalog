package com.example.productcatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String brand;
    private String color;
    private double rating;

    public ProductDTO(long l, String s, String apple, String mobile, String black, double v, double v1) {
    }
}
