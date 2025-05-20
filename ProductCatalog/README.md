# Welcome to the Product Catalog Backend!

This Spring Boot application provides secure, JWT-protected REST APIs to manage products, carts, and wishlists. It supports rich features like:

- **Filtering**: Narrow down products by category, brand, price range, rating, and more
- **Sorting**: Order results by price, rating, or any other attribute
- **Searching**: Full-text search across names, descriptions, brands, and categories
 ---
## 🎬 Video Overview
▶️ [Watch the demo video on Google Drive](https://drive.google.com/file/d/1nUvOvsOSoRaw6pZuYSLNgD4yLd9wNq6x/view?usp=sharing)

---
### 🛠 Tools Required

* **JDK:** Java 21 or higher
* **Build Tool:** Maven
* **IDE (Recommended):** IntelliJ IDEA / VS Code / Eclipse
* **Docker:** For containerized deployment



## 📬 Postman API Test Collection

You can find the full Postman test collection in this GitHub repository:
📂 [krishna\_Patidar\_ProductCatalog Postman Collection](https://github.com/krishnapatidar458/krishna_Patidar_ProductCatalog/blob/main/ProductCatalog/product_catalog.postman_collection.json)

Import the `.json` file into Postman to test all APIs easily.

---

## 📁 Directory Structure (Backend)

```plaintext
krishna_Patidar_ProductCatalog/
├── .gitignore                      # Git ignore rules
├── mvnw                            # Maven wrapper (Linux/Mac)
├── mvnw.cmd                        # Maven wrapper (Windows)
├── pom.xml                         # Project dependencies and plugins
├── Dockerfile                      # Docker instructions for building image
├── ProductCatalog/                 # Backend application module
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/productcatalog/
│   │       │       ├── config/            # Security configurations
│   │       │       │   ├── ModelmapperConfig.java
│   │       │       │   └── SecurityConfig.java
│   │       │       │
│   │       │       ├── controller/        # REST Controllers
│   │       │       │   ├── AuthController.java
│   │       │       │   ├── CartController.java
│   │       │       │   ├── ProductController.java
│   │       │       │   ├── SearchController.java
│   │       │       │   └── WishlistController.java
│   │       │       │
│   │       │       ├── dto/               # Data Transfer Objects
│   │       │       │   ├── AuthRequest.java
│   │       │       │   ├── AuthResponse.java
│   │       │       │   ├── CartItemDTO.java
│   │       │       │   ├── FilterRequest.java
│   │       │       │   ├── ProductDTO.java
│   │       │       │   ├── SearchRequest.java
│   │       │       │   └── WishlistItemDTO.java
│   │       │       │
│   │       │       ├── entity/            # JPA Entities
│   │       │       │   ├── CartItem.java
│   │       │       │   ├── Product.java
│   │       │       │   ├── User.java
│   │       │       │   └── WishlistItem.java
│   │       │       │
│   │       │       ├── enums/             # Enum Definitions
│   │       │       │   └── SortOption.java
│   │       │       │
│   │       │       ├── exception/         # Custom exception handling
│   │       │       │   ├── GlobalExceptionHandler.java
│   │       │       │   └── ProductNotFoundException.java
│   │       │       │
│   │       │       ├── repository/        # Spring Data JPA repositories
│   │       │       │   ├── CartItemRepository.java
│   │       │       │   ├── ProductRepository.java
│   │       │       │   ├── UserRepository.java
│   │       │       │   └── WishlistRepository.java
│   │       │       │
│   │       │       ├── service/           # Business logic layer
│   │       │       │   ├── Impl
│   │       │       │   │   ├── CartItemServiceImpl.java
│   │       │       │   │   ├── ProductServiceImpl.java
│   │       │       │   │   ├── WishlistServiceImpl.java
│   │       │       │   ├── CartItemService.java
│   │       │       │   ├── CustomUserDetailsService.java
│   │       │       │   ├── ProductService.java
│   │       │       │   └── WishlistService.java
│   │       │       │
│   │       │       ├── security/              # Utility classes
│   │       │       │    └── JwtUtil.java
│   │       │       │    └── JwtRequestFilter.java
│   │       │       │    └── JwtAuthEntryPoint.java
│   │       │       │    └── KeyGenerator.java
│   │       │       │
│   │       │       ├── specification/
│   │       │       │    └── ProductSpecification.java
│   │       │       │
│   │       │       └── ProductCatalogApplication.java
│   │       └── resources/
│   │           ├── application.properties  # Application config
│   │           └── static/                # Static web assets (if any)
│   └── target/                             # Build output (auto-generated)
└── README.md   
```

---

## 📌 Notes

* This backend uses Spring Security + JWT for secure authentication.
* No Swagger UI is included. Use Postman or curl for testing APIs.
* H2 is used for quick testing, but can be switched to PostgreSQL or MySQL.

---

Happy Coding! 🎯

— Developed by [Krishna Patidar](https://github.com/krishnapatidar458)
