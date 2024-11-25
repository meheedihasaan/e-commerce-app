package com.mehedi.ecommerce.clients;

import com.mehedi.ecommerce.models.responses.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(
        name = "product-service",
        url = "${application.config.order-service-url}"
)
public interface OrderClient {

    @GetMapping(value = "/{id}")
    Optional<OrderResponse> findById(@PathVariable UUID id);
}
