package com.mehedi.ecommerce.clients;

import com.mehedi.ecommerce.models.responses.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-service-url}"
)
public interface CustomerClient {

    @GetMapping(value = "/{id}")
    Optional<CustomerResponse> findById(@PathVariable UUID id);
}
