package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.clients.CustomerClient;
import com.mehedi.ecommerce.clients.OrderClient;
import com.mehedi.ecommerce.entities.Payment;
import com.mehedi.ecommerce.exceptions.PaymentException;
import com.mehedi.ecommerce.kafka.NotificationProducer;
import com.mehedi.ecommerce.models.requests.PaymentRequest;
import com.mehedi.ecommerce.models.requests.SendPaymentNotificationRequest;
import com.mehedi.ecommerce.models.responses.CustomerResponse;
import com.mehedi.ecommerce.models.responses.OrderResponse;
import com.mehedi.ecommerce.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CustomerClient customerClient;
    private final NotificationProducer notificationProducer;
    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;

    public UUID processPayment(PaymentRequest request) {
        CustomerResponse customer = customerClient.findById(request.customerId())
                .orElseThrow(() -> new PaymentException("Can not process the payment. Customer not found"));

        OrderResponse order = orderClient.findById(request.orderId())
                .orElseThrow(() -> new PaymentException("Can not process the payment. Order not found"));

        Payment payment = Payment.builder()
                .orderId(request.orderId())
                .customerId(customer.getId())
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
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail()
                )
        );

        return payment.getId();
    }
}
