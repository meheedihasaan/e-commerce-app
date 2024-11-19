package com.mehedi.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

public record UpdateCustomerRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,
        @Validated
        Address address
) { }
