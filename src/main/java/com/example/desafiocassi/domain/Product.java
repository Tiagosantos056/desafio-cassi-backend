package com.example.desafiocassi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;


@NoArgsConstructor
@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double priceBase;
    private String category;
    private LocalDate dateCreated;


    public Product(Long id, String name, String description, Double priceBase, String category, LocalDate dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceBase = priceBase;
        this.category = category;
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(priceBase, product.priceBase) && Objects.equals(category, product.category) && Objects.equals(dateCreated, product.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, priceBase, category, dateCreated);
    }
}
