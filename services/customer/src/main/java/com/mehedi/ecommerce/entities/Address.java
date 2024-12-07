package com.mehedi.ecommerce.entities;

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
