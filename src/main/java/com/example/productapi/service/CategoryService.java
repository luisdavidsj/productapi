package com.example.productapi.service;

import com.example.productapi.model.Category;
import com.example.productapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Optional<Category> getById(Long id) {
        return repository.findById(id);
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public Category update(Long id, Category category) {
        Category existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(category.getName());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
