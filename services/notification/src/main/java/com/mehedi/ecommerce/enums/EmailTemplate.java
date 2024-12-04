package com.mehedi.ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment Confirmation"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order Confirmation");

    private final String templateName;
    private final String subject;
}
