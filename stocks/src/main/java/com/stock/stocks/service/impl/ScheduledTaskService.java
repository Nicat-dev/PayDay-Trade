package com.stock.stocks.service.impl;

import com.stock.stocks.exception.ApplicationException;
import com.stock.stocks.model.entity.Stock;
import com.stock.stocks.model.enums.Exceptions;
import com.stock.stocks.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final StockRepository stockRepository;

    @Scheduled(initialDelay = 40 * 60 * 1000, fixedRate = 40 * 60 * 1000) //40 minute
    public void updateDatabase() throws IOException {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks){
            Optional<Stock> product = stockRepository.findById(stock.getId());

            double randomPrice= ThreadLocalRandom.current().nextDouble(100,800);
            product.orElseThrow(()-> new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION))
                    .setPrice(randomPrice);

            stockRepository.save(product.orElseThrow());
        }
    }

}
