package com.mehedi.ecommerce.models.responses;

import com.mehedi.ecommerce.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
public class OrderResponse {
    private UUID id;
    private String customerId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private String reference;
}
