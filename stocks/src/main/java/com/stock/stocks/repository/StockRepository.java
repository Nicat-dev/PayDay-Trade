package com.stock.stocks.repository;

import com.stock.stocks.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    Stock findByName(String name);
    Stock findBySymbol(String symbol);

}
