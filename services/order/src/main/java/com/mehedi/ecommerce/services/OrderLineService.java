package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.entities.OrderLine;
import com.mehedi.ecommerce.models.requests.CreateOrderLineRequest;
import com.mehedi.ecommerce.repositories.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public List<OrderLine> findAllByOrderId(UUID orderId) {
        return orderLineRepository.findAllByOrderId(orderId);
    }

    public OrderLine findById(UUID id) {
        return orderLineRepository.findById(id).orElse(null);
    }

    public OrderLine create(CreateOrderLineRequest request) {
        OrderLine orderLine = OrderLine.builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .unitPrice(request.unitPrice())
                .totalPrice(request.totalPrice())
                .build();
        return orderLineRepository.save(orderLine);
    }
}
