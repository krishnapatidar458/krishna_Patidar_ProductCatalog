package com.example.productcatalog.specification;

import com.example.productcatalog.entity.Product;
import com.example.productcatalog.enums.SortOption;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductSpecification {

    public static Specification<Product> filterBySortOption(SortOption sortOption) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            switch (sortOption) {
                case PRICE_ASCENDING:
                    return (Predicate) criteriaBuilder.asc(root.get("price"));
                case PRICE_DESCENDING:
                    return (Predicate) criteriaBuilder.desc(root.get("price"));
                case RATING_ASCENDING:
                    return (Predicate) criteriaBuilder.asc(root.get("rating"));
                case RATING_DESCENDING:
                    return (Predicate) criteriaBuilder.desc(root.get("rating"));
                default:
                    return null;
            }
        };
    }
}
