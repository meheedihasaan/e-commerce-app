package com.mehedi.ecommerce.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
