package com.stock.stocks.service.impl;

import com.stock.stocks.exception.ApplicationException;
import com.stock.stocks.mapper.StockMapper;
import com.stock.stocks.model.dto.StockDto;
import com.stock.stocks.model.enums.Exceptions;
import com.stock.stocks.model.request.SellRequest;
import com.stock.stocks.repository.StockRepository;
import com.stock.stocks.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository repository;
    private final StockMapper mapper;

    @Override
    public StockDto findStockByName(String name) {
        return mapper.entityToDto(repository.findByName(name)
                .orElseThrow(()-> new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION)));
    }

    @Override
    public StockDto findStockBySymbol(String symbol) {
        return mapper.entityToDto(repository.findBySymbol(symbol)
                .orElseThrow(()-> new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION)));
    }

    @Override
    public List<StockDto> findAll() {
        List<StockDto> dtoList = mapper.entityListToDtoList(repository.findAll());
        if (!dtoList.isEmpty()){
            return dtoList;
        }else {
            throw new ApplicationException(Exceptions.STOCK_LIST_IS_EMPTY_EXCEPTION);
        }
    }

    @Override
    public double sellStock(SellRequest request) {
        double price = findStockBySymbol(request.getSymbol()).getPrice();
        if (price >= request.getOffer()){
            return price;
        }else {
            return 0;
        }
    }
}
