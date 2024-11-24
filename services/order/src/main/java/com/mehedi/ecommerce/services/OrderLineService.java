package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.entities.OrderLine;
import com.mehedi.ecommerce.models.requests.CreateOrderLineRequest;
import com.mehedi.ecommerce.repositories.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

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
