package com.example.productcatalog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchRequest {
    @NotBlank(message = "Search query must not be blank")
    private String query;
}
