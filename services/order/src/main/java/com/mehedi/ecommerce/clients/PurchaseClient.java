package com.mehedi.ecommerce.clients;

import com.mehedi.ecommerce.models.requests.PurchaseRequest;
import com.mehedi.ecommerce.models.responses.PurchaseResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "purchase-service",
        url = "${application.config.purchase-service-url}"
)
public interface PurchaseClient {

    @PostMapping
    List<PurchaseResponse> purchase(@Valid @RequestBody List<PurchaseRequest> requests);
}
