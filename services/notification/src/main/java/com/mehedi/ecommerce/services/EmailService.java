package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.models.responses.PurchaseResponse;
import com.mehedi.ecommerce.enums.EmailTemplate;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine springTemplateEngine;

    @Async
    public void sendPaymentConfirmationMail(String customerName, String email, BigDecimal amount, String reference) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());

        String templateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplateName();
        String subject = EmailTemplate.PAYMENT_CONFIRMATION.getSubject();

        Map<String, Object> paymentConfirmation = Map.of(
                "customerName", customerName,
                "amount", amount,
                "reference", reference
        );

        Context context = new Context();
        context.setVariables(paymentConfirmation);

        try {
            String htmlContent = springTemplateEngine.process(templateName, context);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            log.info("Payment confirmation email sent successfully to {}", email);
        } catch (Exception e) {
            log.warn("Failed to send email", e);
        }
    }

    @Async
    public void sendOrderConfirmationMail(String customerName, String email, BigDecimal totalAmount, String reference, List<PurchaseResponse> purchases) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());

        String templateName = EmailTemplate.ORDER_CONFIRMATION.getTemplateName();
        String subject = EmailTemplate.ORDER_CONFIRMATION.getSubject();

        Map<String, Object> orderConfirmation = Map.of(
                "customerName", customerName,
                "totalAmount", totalAmount,
                "reference", reference,
                "purchases", purchases
        );

        Context context = new Context();
        context.setVariables(orderConfirmation);

        try {
            String htmlContent = springTemplateEngine.process(templateName, context);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
            log.info("Order confirmation email sent successfully to {}", email);
        } catch (Exception e) {
            log.warn("Failed to send email", e);
        }
    }
}
