package com.customer.customers.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DepositRequest(

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Password must be alphanumeric")
        String password,

        @Min(value = 0, message = "Money must be a positive value")
        double money

) {
}
