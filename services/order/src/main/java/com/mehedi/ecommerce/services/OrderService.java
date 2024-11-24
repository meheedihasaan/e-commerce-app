package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.clients.CustomerClient;
import com.mehedi.ecommerce.clients.PurchaseClient;
import com.mehedi.ecommerce.entities.Order;
import com.mehedi.ecommerce.entities.OrderLine;
import com.mehedi.ecommerce.exceptions.BusinessException;
import com.mehedi.ecommerce.models.requests.CreateOrderLineRequest;
import com.mehedi.ecommerce.models.requests.CreateOrderRequest;
import com.mehedi.ecommerce.models.responses.CustomerResponse;
import com.mehedi.ecommerce.models.responses.PurchaseResponse;
import com.mehedi.ecommerce.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final PurchaseClient purchaseClient;

    public void createOrder(CreateOrderRequest request) {
        CustomerResponse customer = customerClient.findById(request.customerId())
                .orElseThrow(()-> new BusinessException("Can not place the order. Customer not found"));

        List<PurchaseResponse> purchases = purchaseClient.purchase(request.purchases());

        List<OrderLine> orderLines = new ArrayList<>();
        purchases.forEach((purchase)-> {
            CreateOrderLineRequest orderLineRequest = new CreateOrderLineRequest(
                    purchase.getProductId(),
                    purchase.getQuantity(),
                    purchase.getPrice(),
                    purchase.getPrice().multiply(new BigDecimal(purchase.getQuantity()))
            );

            OrderLine orderLine = orderLineService.create(orderLineRequest);
            orderLines.add(orderLine);
        });

        Order order = Order.builder()
                .customerId(customer.getId())
                .paymentMethod(request.paymentMethod())
                .totalAmount(orderLines.stream().map(OrderLine::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .reference(request.reference())
                .orderLines(orderLines)
                .build();

        orderRepository.save(order);
    }
}
