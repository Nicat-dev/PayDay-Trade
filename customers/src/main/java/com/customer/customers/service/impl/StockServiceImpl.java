package com.customer.customers.service.impl;

import com.customer.customers.model.dto.BuySellRequestDto;
import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.repository.CustomerStockRepository;
import com.customer.customers.service.StockService;
import com.customer.customers.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final CustomerStockRepository repository;
    private final WalletService service;

    @Override
    public ResponseDto buyStock(BuySellRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseDto sellStock(BuySellRequestDto requestDto) {
        return null;
    }
}
