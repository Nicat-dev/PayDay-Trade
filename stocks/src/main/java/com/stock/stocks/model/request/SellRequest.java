package com.stock.stocks.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SellRequest(

        @NotBlank(message = "symbol required")
        String symbol,
        @NotNull(message = "offer required")
        int offer
) {
}
