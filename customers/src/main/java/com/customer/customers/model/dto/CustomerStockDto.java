package com.customer.customers.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStockDto {
    Long id;
    String symbol;
    String currency;
    String exchange;
    String mic_code;
    String country;
    String types;
    double buyPrice;
    double sellPrice;
    boolean buyStatus;
    boolean sellStatus;
    boolean sellRequest;
    Long userId;
}
