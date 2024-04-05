package com.customer.customers.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_stock")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String symbol;
    String currency;
    String exchange;
    String mic_code;
    String country;
    String types;
    double buyPrice;
    double sellPrice;
    @Builder.Default
    boolean buyStatus = false;
    @Builder.Default
    boolean sellStatus = false;
    @Builder.Default
    boolean sellRequest = false;

    @ManyToOne
    Customer customer;
    
}
