# Product Catalog - Spring Boot Backend

This is a **backend-only** project for managing a product catalog using **Spring Boot** and **JWT-based authentication**. It supports functionalities like product management, filtering, searching, cart and wishlist operations.

---

## 🚀 How to Run the Backend

### 🛠 Tools Required

* **JDK:** Java 17 or higher
* **Build Tool:** Maven
* **IDE (Recommended):** IntelliJ IDEA / VS Code / Eclipse
* **Docker (Optional):** For containerized deployment

### 🔧 Run Locally (Without Docker)

1. **Clone the repository:**

   ```bash
   git clone https://github.com/krishnapatidar458/krishna_Patidar_ProductCatalog.git
   cd krishna_Patidar_ProductCatalog/ProductCatalog
   ```

2. **Build and run the application:**

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

3. **App runs on:**

   ```
   http://localhost:8081
   ```

---

## 🐳 Run with Docker

### 📄 Dockerfile Highlights

Uses multi-stage build with Maven and Eclipse Temurin JDK.

### 🧱 Build Image

```bash
docker build -t product-catalog .
```

### ▶️ Run Container (use a free port like 8082 if 8081 is occupied)

```bash
docker run -p 8082:8081 product-catalog
```

---

## 📬 Sample API Usage

> Authentication is required for most endpoints. Use the `/api/auth/login` endpoint first.

### 🔐 Login (JWT)

**Endpoint:** `POST /api/auth/login`

```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**

```json
{
  "token": "<JWT_TOKEN>"
}
```

Use the JWT token in the header for authenticated requests:

```
Authorization: Bearer <JWT_TOKEN>
```

---

### 📦 Products

**Create Product:**

```
POST /api/products
```

```json
{
  "name": "iPhone 14",
  "description": "Latest Apple phone",
  "price": 999.99,
  "category": "Smartphones",
  "brand": "Apple",
  "color": "Black",
  "rating": 4.8
}
```

**Filter Products:**

```
POST /api/products/filter
```

```json
{
  "category": "Smartphones",
  "minPrice": 500,
  "maxPrice": 1500
}
```

**Search Products:**

```
POST /api/search
```

```json
{
  "query": "iphone"
}
```

**Sorted Products:**

```
GET /api/products/sorted?sortOption=PRICE_ASC
```

---

### 🛒 Cart Items

**Add to Cart:**

```
POST /api/cart-items
```

```json
{
  "productId": 1
}
```

**Get All Cart Items:**

```
GET /api/cart-items
```

---

### 💖 Wishlist

**Add to Wishlist:**

```
POST /api/wishlist
```

```json
{
  "productId": 2
}
```

**Get Wishlist:**

```
GET /api/wishlist
```

---

## 📄 H2 Console

You can view the in-memory H2 database here:

```
http://localhost:8081/h2-console
```

**JDBC URL:** `jdbc:h2:mem:productdb`
**Username:** `krishna`
**Password:** `pass`

---

## 📬 Postman API Test Collection

You can find the full Postman test collection in this GitHub repository:
📂 [krishna\_Patidar\_ProductCatalog Postman Collection](https://github.com/krishnapatidar458/krishna_Patidar_ProductCatalog/tree/main/Postman)

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
├── README.md                       # Main project documentation
├── ProductCatalog/                 # Backend application module
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/example/productcatalog/
│   │       │       ├── config/            # Security configurations
│   │       │       │   ├── ModelmapperConfig.java
│   │       │       │   └── SecurityConfig.java
│   │       │       │
│   │       │       ├── filter/            # JWT request filtering
│   │       │       │   └── JwtRequestFilter.java
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
│   │       │       └── util/              # Utility classes
│   │       │           └── JwtUtil.java
│   │       │       └── ProductCatalogApplication.java
│   │       │
│   │       └── resources/
│   │           ├── application.properties  # Application config
│   │           └── static/                # Static web assets (if any)
│   │           └── data.sql
│   │           └── schema.sql 
│   └── target/                             # Build output (auto-generated)
└──
```

---

## 📌 Notes

* This backend uses Spring Security + JWT for secure authentication.
* No Swagger UI is included. Use Postman or curl for testing APIs.
* H2 is used for quick testing, but can be switched to PostgreSQL or MySQL.

---

Happy Coding! 🎯

— Developed by [Krishna Patidar](https://github.com/krishnapatidar458)
