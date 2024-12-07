package com.mehedi.ecommerce.models.requests;

import com.mehedi.ecommerce.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendPaymentNotificationRequest {
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private String reference;
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private UUID orderId;
}
