package com.mehedi.ecommerce.controllers;

import com.mehedi.ecommerce.entities.OrderLine;
import com.mehedi.ecommerce.services.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@RequestMapping(value = "/api/orderlines/")
public class OrderLineController {

    private final OrderLineService orderLineService;

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<List<OrderLine>> findAllByOrderId(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderLineService.findAllByOrderId(orderId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderLine> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderLineService.findById(id));
    }
}