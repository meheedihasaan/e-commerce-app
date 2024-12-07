package com.mehedi.ecommerce.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record PurchaseRequest(

        @NotNull(message = "Customer ID is required.")
        UUID productId,

        @Positive(message = "Quantity must be positive number.")
        Integer quantity
) { }
