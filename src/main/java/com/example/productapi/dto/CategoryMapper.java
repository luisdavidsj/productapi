package com.example.productapi.dto;

import com.example.productapi.model.Category;

public class CategoryMapper {
    public static Category toEntity(CategoryRequestDTO dto) {
        return Category.builder()
                .name(dto.name())
                .build();
    }

    public static CategoryResponseDTO toDTO(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName()
        );
    }
}