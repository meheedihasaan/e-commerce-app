package com.mehedi.ecommerce.models.responses;

import com.mehedi.ecommerce.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderConfirmation {
    private PaymentMethod paymentMethod;
    private BigDecimal totalAmount;
    private String reference;
    private CustomerResponse customer;
    private List<PurchaseResponse> purchases;
}
