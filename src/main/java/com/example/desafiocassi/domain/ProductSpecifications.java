package com.example.desafiocassi.domain;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> nameContains(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> categoryContains(String category) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("category"), "%" + category + "%");
    }

    public static Specification<Product> descriptionContains(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }
}
