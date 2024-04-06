package com.stock.stocks.service;

import com.stock.stocks.model.dto.StockDto;
import com.stock.stocks.model.request.SellRequest;

import java.util.List;

public interface StockService {

    StockDto findStockByName(String name);
    StockDto findStockBySymbol(String symbol);
    List<StockDto> findAll();
    double sellStock(SellRequest request);

}
