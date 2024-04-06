package com.customer.customers.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuySellRequestDto {
    @NotBlank(message = "Symbol is required")
    private String symbol;

    @NotBlank(message = "Username is required")
    private String username;

    @Min(value = 0, message = "Money must be a positive value")
    private int offer;
}
