package com.mehedi.ecommerce.kafka;

import com.mehedi.ecommerce.models.requests.SendPaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, SendPaymentNotificationRequest> kafkaTemplate;

    public void sendPaymentNotification(SendPaymentNotificationRequest sendPaymentNotificationRequest) {
        log.info("Sending payment notification to Kafka topic");

        Message<SendPaymentNotificationRequest> message = MessageBuilder
                .withPayload(sendPaymentNotificationRequest)
                .setHeader("topic", "payment-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
