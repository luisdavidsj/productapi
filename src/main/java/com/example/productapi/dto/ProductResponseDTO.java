package com.example.productapi.dto;

public record ProductResponseDTO(
    Long id,
    String name,
    String description,
    double price,
    Long categoryId,
    String categoryName
) {}