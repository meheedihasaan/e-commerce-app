package com.mehedi.ecommerce.models.requests;

import jakarta.validation.constraints.NotBlank;

public record CreateUpdateAddressRequest(

        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "House number is required")
        String houseNumber,

        @NotBlank(message = "Zip code is required")
        String zipCode
) { }
