package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.entities.Notification;
import com.mehedi.ecommerce.models.responses.OrderConfirmation;
import com.mehedi.ecommerce.models.responses.PaymentConfirmation;
import com.mehedi.ecommerce.enums.NotificationType;
import com.mehedi.ecommerce.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public String create(NotificationType type, OrderConfirmation orderConfirmation, PaymentConfirmation paymentConfirmation) {
        Notification notification = Notification.builder()
                .type(type)
                .orderConfirmation(orderConfirmation)
                .paymentConfirmation(paymentConfirmation)
                .createdAt(LocalDateTime.now())
                .build();

        notification = notificationRepository.save(notification);
        return notification.getId();
    }
}
