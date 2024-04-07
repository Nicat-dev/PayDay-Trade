package com.customer.customers.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellRequestDto {
    private String symbol;
    private double offerPrice;
}
