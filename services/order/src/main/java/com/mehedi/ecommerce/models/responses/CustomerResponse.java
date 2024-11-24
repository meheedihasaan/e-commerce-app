package com.mehedi.ecommerce.models.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class CustomerResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
