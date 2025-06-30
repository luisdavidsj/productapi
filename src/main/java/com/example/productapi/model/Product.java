package com.example.productapi.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
