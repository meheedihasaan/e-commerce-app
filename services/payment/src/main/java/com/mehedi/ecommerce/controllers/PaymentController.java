package com.mehedi.ecommerce.controllers;

import com.mehedi.ecommerce.models.requests.PaymentRequest;
import com.mehedi.ecommerce.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(value = "/process")
    public ResponseEntity<Void> process(@Valid @RequestBody PaymentRequest request) {
        UUID paymentId = paymentService.process(request);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(paymentId).toUri()).build();
    }
}
