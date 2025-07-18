package com.example.productapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
    @NotBlank String name
) {}
