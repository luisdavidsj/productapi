package com.example.productapi.dto;

import com.example.productapi.model.Product;
import com.example.productapi.model.Category;

public class ProductMapper {
    public static Product toEntity(ProductRequestDTO dto, Category category) {
        return Product.builder()
                .name(dto.name())
                .description(dto.description())
                .price(dto.price())
                .category(category)
                .build();
    }

    public static ProductResponseDTO toDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }
}