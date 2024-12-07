package com.mehedi.ecommerce.entities;

import com.mehedi.ecommerce.enums.NotificationType;
import com.mehedi.ecommerce.models.responses.OrderConfirmation;
import com.mehedi.ecommerce.models.responses.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
    private LocalDateTime createdAt;
}
