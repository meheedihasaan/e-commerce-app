package com.mehedi.ecommerce.customer;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;
}
