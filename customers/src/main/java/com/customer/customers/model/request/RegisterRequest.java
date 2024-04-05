package com.customer.customers.model.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @NotBlank(message = "name cannot be blank") String name,
        @NotBlank(message = "surname cannot be blank") String surname,
        @NotBlank(message = "username cannot be blank") String username,
        @NotBlank(message = "password cannot be blank") String password,
        @NotBlank(message = "email cannot be blank") String email

) {
}
