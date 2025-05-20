package com.example.productcatalog.specification;

import com.example.productcatalog.dto.FilterRequest;
import com.example.productcatalog.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filterBy(FilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getCategories() != null && !filter.getCategories().isEmpty()) {
                predicates.add(root.get("category").in(filter.getCategories()));
            }

            if (filter.getBrands() != null && !filter.getBrands().isEmpty()) {
                predicates.add(root.get("brand").in(filter.getBrands()));
            }

            if (filter.getColors() != null && !filter.getColors().isEmpty()) {
                predicates.add(root.get("color").in(filter.getColors()));
            }

            if (filter.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
            }

            if (filter.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
            }

            if (filter.getMinRating() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("rating"), filter.getMinRating()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Product> searchByQuery(String query) {
        return (root, query1, cb) -> {
            String likePattern = "%" + query.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("name")), likePattern),
                    cb.like(cb.lower(root.get("description")), likePattern),
                    cb.like(cb.lower(root.get("brand")), likePattern),
                    cb.like(cb.lower(root.get("category")), likePattern)
            );
        };
    }
}

