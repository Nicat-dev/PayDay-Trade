package com.stock.stocks.service.impl;

import com.stock.stocks.model.dto.StockDto;
import com.stock.stocks.model.request.SellRequest;
import com.stock.stocks.repository.StockRepository;
import com.stock.stocks.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    //private final StockRepository repository;

    @Override
    public StockDto findStockByName(String name) {
        return null;
    }

    @Override
    public StockDto findStockBySymbol(String symbol) {
        return null;
    }

    @Override
    public List<StockDto> findAll() {
        return null;
    }

    @Override
    public int sellStock(SellRequest request) {
        return 0;
    }
}
