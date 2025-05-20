package com.example.productcatalog.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String category;
    private String brand;
    private String color;
    private double rating;

    @Column(name = "image_url")
    private String imageUrl;
}
