package com.example.productapi.service;

import com.example.productapi.dto.CategoryMapper;
import com.example.productapi.dto.CategoryRequestDTO;
import com.example.productapi.dto.CategoryResponseDTO;
import com.example.productapi.model.Category;
import com.example.productapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        Category category = CategoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return CategoryMapper.toDTO(saved);
    }

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryResponseDTO findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryMapper.toDTO(category);
    }

    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(dto.name());
        Category updated = categoryRepository.save(category);
        return CategoryMapper.toDTO(updated);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
