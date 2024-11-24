package com.mehedi.ecommerce.models.responses;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseResponse {
    private UUID productId;
    private String productName;
    private String description;
    private Integer quantity;
    private BigDecimal price;
}
