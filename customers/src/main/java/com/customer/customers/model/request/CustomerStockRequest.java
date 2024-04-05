package com.customer.customers.model.request;

public record CustomerStockRequest(

        String symbol,
        String currency,
        String exchange,
        String mic_code,
        String country,
        String types

) {
}
