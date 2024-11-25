package com.mehedi.ecommerce.models.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
