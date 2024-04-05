package com.customer.customers.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerDto {

    Long id;
    String name;
    String surname;
    String username;
    String email;
    double wallet;

}
