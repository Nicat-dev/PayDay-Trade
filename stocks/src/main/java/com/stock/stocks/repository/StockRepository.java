package com.stock.stocks.repository;

import com.stock.stocks.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    Optional<Stock> findByName(String name);
    Optional<Stock> findBySymbol(String symbol);

}
