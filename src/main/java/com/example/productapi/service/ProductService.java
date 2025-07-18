package com.example.productapi.service;

import com.example.productapi.dto.ProductMapper;
import com.example.productapi.dto.ProductRequestDTO;
import com.example.productapi.dto.ProductResponseDTO;
import com.example.productapi.model.Category;
import com.example.productapi.model.Product;
import com.example.productapi.repository.CategoryRepository;
import com.example.productapi.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseDTO create(ProductRequestDTO dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = ProductMapper.toEntity(dto, category);
        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(saved);
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toDTO(product);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setCategory(category);

        Product updated = productRepository.save(product);
        return ProductMapper.toDTO(updated);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> search(String name, Double minPrice, Double maxPrice, Pageable pageable) {
    return productRepository.findAll((root, query, cb) -> {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (minPrice != null) {
            predicates.add(cb.ge(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicates.add(cb.le(root.get("price"), maxPrice));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }, pageable);
}
}
