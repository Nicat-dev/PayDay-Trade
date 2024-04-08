package com.customer.customers.controller;

import com.customer.customers.model.dto.BuySellRequestDto;
import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class CustomerStockController {

    private final StockService service;

    @PostMapping("/buy")
    public ResponseEntity<ResponseDto> buy(@Valid @RequestBody BuySellRequestDto requestDto){
        var dto = service.buyStock(requestDto);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/sell")
    public ResponseEntity<ResponseDto> sell(@Valid @RequestBody BuySellRequestDto requestDto){
        var dto = service.sellStock(requestDto);
        return ResponseEntity.ok().body(dto);
    }

}
