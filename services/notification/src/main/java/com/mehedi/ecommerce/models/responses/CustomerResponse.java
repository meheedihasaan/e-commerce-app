package com.mehedi.ecommerce.models.responses;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
