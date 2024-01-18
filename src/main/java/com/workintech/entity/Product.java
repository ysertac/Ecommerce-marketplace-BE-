package com.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "ecommerce")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotNull
    @Size(min = 2, max = 20, message = ("Product name must contain at least 2 letters and max 20 letters"))
    private String name;

    @Column(name = "description")
    @Size(max = 100, message = "Max 100 letters")
    private String description;

    @Column(name = "rating")
    @NotNull
    @Max(value = 5, message = "Rating may be 5 as max.")
    private double rating;

    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "image")
    private String image;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private Category category;

}
