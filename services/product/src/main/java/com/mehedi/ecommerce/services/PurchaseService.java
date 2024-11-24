package com.mehedi.ecommerce.services;

import com.mehedi.ecommerce.entities.Product;
import com.mehedi.ecommerce.exceptions.ProductPurchaseException;
import com.mehedi.ecommerce.models.requests.PurchaseRequest;
import com.mehedi.ecommerce.models.responses.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final ProductService productService;

    public List<PurchaseResponse> purchase(List<PurchaseRequest> requests) {
        List<PurchaseResponse> purchaseResponses = new ArrayList<>();
        requests.forEach((request)-> {
            Product product = productService.findById(request.productId());
            if (product.getQuantity() < request.quantity()) {
                throw new ProductPurchaseException("Product quantity is not enough.");
            }

            PurchaseResponse purchaseResponse = PurchaseResponse.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .description(product.getDescription())
                    .quantity(request.quantity())
                    .price(product.getPrice())
                    .build();

            purchaseResponses.add(purchaseResponse);
        });

        return purchaseResponses;
    }
}
