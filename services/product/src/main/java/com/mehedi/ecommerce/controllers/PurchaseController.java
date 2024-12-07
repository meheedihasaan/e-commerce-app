package com.mehedi.ecommerce.controllers;

import com.mehedi.ecommerce.models.responses.PurchaseResponse;
import com.mehedi.ecommerce.models.requests.PurchaseRequest;
import com.mehedi.ecommerce.services.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/products/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<List<PurchaseResponse>> create(@Valid @RequestBody List<PurchaseRequest> requests) {
        return ResponseEntity.ok(purchaseService.purchase(requests));
    }
}
