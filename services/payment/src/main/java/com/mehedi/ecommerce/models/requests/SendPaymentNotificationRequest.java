package com.mehedi.ecommerce.models.requests;

import com.mehedi.ecommerce.enums.PaymentMethod;

import java.math.BigDecimal;

public record SendPaymentNotificationRequest(
        PaymentMethod paymentMethod,
        BigDecimal amount,
        String reference,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) { }
