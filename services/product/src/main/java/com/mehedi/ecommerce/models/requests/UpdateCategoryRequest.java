package com.mehedi.ecommerce.models.requests;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(
        @NotBlank(message = "Category name is required.")
        String name,
        String description
) { }
