-- Products Table
INSERT INTO products (id, name, brand, category, color, price, rating)
VALUES
  (1, 'iPhone 14', 'Apple', 'Mobile', 'Black', 999.99, 4.8),
  (2, 'Galaxy S22', 'Samsung', 'Mobile', 'White', 849.99, 4.6),
  (3, 'Pixel 7', 'Google', 'Mobile', 'Black', 699.99, 4.5),
  (4, 'Xperia 5', 'Sony', 'Mobile', 'Blue', 799.99, 4.4);

-- Wishlist Table (optional, assuming user_id is not used for now)
INSERT INTO wishlist_items (id, product_id)
VALUES
  (1, 1),
  (2, 3);

-- Cart Table (optional)
INSERT INTO cart_items (id, product_id, quantity)
VALUES
  (1, 2, 1),
  (2, 3, 2);
