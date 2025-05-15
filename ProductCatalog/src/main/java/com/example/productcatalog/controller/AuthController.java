//package com.example.productcatalog.controller;
//
////import com.example.productcatalog.security.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @PostMapping("/login")
//    public Map<String, String> authenticate(@RequestBody Map<String, String> loginData) {
//        // For simplicity, assuming the username and password are correct.
//        // Implement your authentication logic here.
//        String token = jwtTokenProvider.generateToken(loginData.get("username"));
//        return Map.of("token", token);
//    }
//}
