package com.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories", schema = "ecommerce")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    @NotNull
    @Size(min = 2, max = 20, message = ("Category name must contain at least 2 letters and max 20 latters "))
    private String title;

    @Column(name = "img")
    private String img;

    @Column(name = "rating")
    @NotNull
    @Max(value = 5, message = "Rating may be 5 as max value.")
    private double rating;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> productList;

    public void addProduct(Product product) {
        if (productList == null) {
            productList = new ArrayList<>();
        }
        productList.add(product);
    }
}
