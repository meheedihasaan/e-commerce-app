package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.entities.Payment;
import com.mehedi.ecommerce.kafka.NotificationProducer;
import com.mehedi.ecommerce.models.requests.PaymentRequest;
import com.mehedi.ecommerce.models.requests.SendPaymentNotificationRequest;
import com.mehedi.ecommerce.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final NotificationProducer notificationProducer;
    private final PaymentRepository paymentRepository;

    public UUID process(PaymentRequest request) {
        log.info("Processing payment for order: {}", request.orderId());
        Payment payment = Payment.builder()
                .orderId(request.orderId())
                .customerId(request.customerId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .reference(request.reference())
                .build();

        payment = paymentRepository.save(payment);

        notificationProducer.sendPaymentNotification(
                new SendPaymentNotificationRequest(
                        payment.getPaymentMethod(),
                        payment.getAmount(),
                        payment.getReference(),
                        request.customerId(),
                        request.firstName(),
                        request.lastName(),
                        request.email(),
                        payment.getOrderId())
        );

        return payment.getId();
    }
}
