package com.customer.customers.exception;

import com.customer.customers.model.enums.Exceptions;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final Exceptions exception;

    public ApplicationException(Exceptions exception) {
        super(exception.getMessage());
        this.exception = exception;
    }
}
