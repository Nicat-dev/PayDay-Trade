package com.customer.customers.service;

import com.customer.customers.model.client.Stock;
import com.customer.customers.model.dto.SellRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stocks", url = "http://localhost:8080/api/v1/stock")
public interface StockClient {

    @GetMapping("/symbol")
    Stock findStockBySymbol(@RequestParam("symbol") String symbol);

    @GetMapping("/name")
    Stock findStockByName(@RequestParam("name") String name);

    @PostMapping("/sell")
    double sellStock(@RequestBody SellRequestDto request);

}
