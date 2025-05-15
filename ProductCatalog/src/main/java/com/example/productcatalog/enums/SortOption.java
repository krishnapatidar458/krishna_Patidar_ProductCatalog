package com.example.productcatalog.enums;

public enum SortOption {
    PRICE_ASCENDING("Price: Low to High"),
    PRICE_DESCENDING("Price: High to Low"),
    RATING_ASCENDING("Rating: Low to High"),
    RATING_DESCENDING("Rating: High to Low");

    private final String displayValue;

    SortOption(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
