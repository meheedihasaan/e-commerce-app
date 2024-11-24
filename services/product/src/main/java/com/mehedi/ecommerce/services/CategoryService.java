package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.exceptions.NotFoundException;
import com.mehedi.ecommerce.models.requests.CreateCategoryRequest;
import com.mehedi.ecommerce.models.requests.UpdateCategoryRequest;
import com.mehedi.ecommerce.entities.Category;
import com.mehedi.ecommerce.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category findById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Category not found."));
    }

    public UUID create(CreateCategoryRequest request) {
        Category category = Category.builder()
                .name(request.name())
                .description(request.description())
                .build();

        category = categoryRepository.save(category);
        return category.getId();
    }

    public void update(UUID id, UpdateCategoryRequest request) {
        Category category = findById(id);
        category.setName(request.name());
        category.setDescription(request.description());
        categoryRepository.save(category);
    }

    public void delete(UUID id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
