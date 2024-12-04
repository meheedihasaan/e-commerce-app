package com.mehedi.ecommerce.models.requests;

import com.mehedi.ecommerce.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record SendPaymentNotificationRequest(
        PaymentMethod paymentMethod,
        BigDecimal amount,
        String reference,
        String customerId,
        String customerFirstName,
        String customerLastName,
        String customerEmail,
        UUID orderId
) { }
