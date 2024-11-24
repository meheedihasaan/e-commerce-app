package com.mehedi.ecommerce.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductRequest(
        @NotBlank(message = "Product name is required.")
        String name,
        String description,
        @Positive(message = "Quantity must be positive number.")
        Integer quantity,
        @Positive(message = "Price must be positive number.")
        BigDecimal price,
        @NotNull(message = "Category ID is required.")
        UUID categoryId
) { }
