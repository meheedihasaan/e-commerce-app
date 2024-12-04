package com.mehedi.ecommerce.clients;

import com.mehedi.ecommerce.models.requests.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-service-url}"
)
public interface PaymentClient {

    @PostMapping(value = "/process")
    UUID process(@RequestBody PaymentRequest request);
}
