package com.example.productapi.controller;

import com.example.productapi.dto.CategoryRequestDTO;
import com.example.productapi.dto.CategoryResponseDTO;
import com.example.productapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryResponseDTO create(@Valid @RequestBody CategoryRequestDTO dto) {
        return categoryService.create(dto);
    }

    @GetMapping
    public List<CategoryResponseDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO dto) {
        return categoryService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
