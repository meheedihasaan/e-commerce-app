package com.mehedi.ecommerce.models.requests;

import com.mehedi.ecommerce.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        @NotNull(message = "Customer ID is required.")
        UUID customerId,
        @NotNull(message = "Payment method is required.")
        PaymentMethod paymentMethod,
        String reference,
        @NotEmpty(message = "You must purchase at least one product to place an order.")
        List<PurchaseRequest> purchases
) { }
