package com.mehedi.ecommerce.models.requests;

import com.mehedi.ecommerce.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
        PaymentMethod paymentMethod,
        BigDecimal amount,
        String reference,
        String customerId,
        String firstName,
        String lastName,
        String email,
        UUID orderId
        ) { }

