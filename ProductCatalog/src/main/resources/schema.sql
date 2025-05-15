-- Table: products
CREATE TABLE products (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    brand VARCHAR(100),
    category VARCHAR(100),
    color VARCHAR(50),
    price DOUBLE,
    rating DOUBLE
);

-- Table: wishlist_items
CREATE TABLE wishlist_items (
    id BIGINT PRIMARY KEY,
    product_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Table: cart_items
CREATE TABLE cart_items (
    id BIGINT PRIMARY KEY,
    product_id BIGINT,
    quantity INT,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
