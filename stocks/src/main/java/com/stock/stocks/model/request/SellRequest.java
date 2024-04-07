package com.stock.stocks.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellRequest{
        @NotBlank(message = "symbol required")
        String symbol;
        @NotNull(message = "offer required")
        double offer;
}
