package com.customer.customers.model.client;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stock {
    Long id;
    String symbol;
    String name;
    String currency;
    String exchange;
    String mic_code;
    String country;
    String type;
    double price;
}
