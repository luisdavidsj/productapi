package com.example.productapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequestDTO(
    @NotBlank String name,
    String description,
    @Positive double price,
    @NotNull Long categoryId
) {}