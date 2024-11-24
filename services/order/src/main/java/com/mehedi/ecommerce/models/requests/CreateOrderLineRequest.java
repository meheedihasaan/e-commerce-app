package com.mehedi.ecommerce.models.requests;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderLineRequest(
        UUID productId,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) { }
