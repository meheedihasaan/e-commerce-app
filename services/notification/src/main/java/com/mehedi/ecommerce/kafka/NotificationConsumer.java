package com.mehedi.ecommerce.kafka;

import com.mehedi.ecommerce.models.responses.CustomerResponse;
import com.mehedi.ecommerce.models.responses.OrderConfirmation;
import com.mehedi.ecommerce.models.responses.PaymentConfirmation;
import com.mehedi.ecommerce.enums.NotificationType;
import com.mehedi.ecommerce.services.EmailService;
import com.mehedi.ecommerce.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {

    private final EmailService emailService;

    private final NotificationService notificationService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        log.info("Consuming payment confirmation from Kafka topic: {}", paymentConfirmation);
        notificationService.create(NotificationType.PAYMENT_CONFIRMATION, null, paymentConfirmation);

        String customerName = paymentConfirmation.getFirstName() + " " + paymentConfirmation.getLastName();
        emailService.sendPaymentConfirmationMail(customerName, paymentConfirmation.getEmail(), paymentConfirmation.getAmount(), paymentConfirmation.getReference());
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Consuming order confirmation from Kafka topic: {}", orderConfirmation);
        notificationService.create(NotificationType.ORDER_CONFIRMATION, orderConfirmation, null);

        CustomerResponse customer = orderConfirmation.getCustomer();
        String customerName = customer.getFirstName() + " " + customer.getLastName();
        emailService.sendOrderConfirmationMail(customerName, customer.getEmail(), orderConfirmation.getTotalAmount(), orderConfirmation.getReference(), orderConfirmation.getPurchases());
    }
}
