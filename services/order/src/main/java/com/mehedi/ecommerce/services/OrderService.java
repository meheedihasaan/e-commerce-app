package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.clients.CustomerClient;
import com.mehedi.ecommerce.clients.PaymentClient;
import com.mehedi.ecommerce.clients.PurchaseClient;
import com.mehedi.ecommerce.entities.Order;
import com.mehedi.ecommerce.entities.OrderLine;
import com.mehedi.ecommerce.exceptions.BusinessException;
import com.mehedi.ecommerce.kafka.OrderProducer;
import com.mehedi.ecommerce.models.requests.CreateOrderLineRequest;
import com.mehedi.ecommerce.models.requests.CreateOrderRequest;
import com.mehedi.ecommerce.models.requests.PaymentRequest;
import com.mehedi.ecommerce.models.responses.CustomerResponse;
import com.mehedi.ecommerce.models.responses.OrderConfirmation;
import com.mehedi.ecommerce.models.responses.PurchaseResponse;
import com.mehedi.ecommerce.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    private final PurchaseClient purchaseClient;

    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order findById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Order not found."));
    }

    public UUID create(CreateOrderRequest request) {
        CustomerResponse customer = customerClient.findById(request.customerId());
        if (Objects.isNull(customer)) {
            throw new BusinessException("Can not place the order. Customer not found");
        }

        List<PurchaseResponse> purchases = purchaseClient.purchase(request.purchases());

        List<OrderLine> orderLines = new ArrayList<>();
        purchases.forEach((purchase)-> {
            CreateOrderLineRequest orderLineRequest = new CreateOrderLineRequest(
                    purchase.getProductId(),
                    purchase.getQuantity(),
                    purchase.getUnitPrice(),
                    purchase.getTotalPrice()
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

        order = orderRepository.save(order);

        PaymentRequest paymentRequest = new PaymentRequest(
                request.paymentMethod(),
                order.getTotalAmount(),
                order.getReference(),
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                order.getId()
        );

        paymentClient.process(paymentRequest);

        OrderConfirmation orderConfirmation = OrderConfirmation.builder()
                .customer(customer)
                .paymentMethod(request.paymentMethod())
                .totalAmount(order.getTotalAmount())
                .reference(order.getReference())
                .purchases(purchases)
                .build();

        orderProducer.sendOrderConfirmation(orderConfirmation);

        return order.getId();
    }
}
