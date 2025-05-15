package com.example.productcatalog.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private final String username;
    private final String password;
}
