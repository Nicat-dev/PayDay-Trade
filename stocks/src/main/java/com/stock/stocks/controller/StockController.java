package com.stock.stocks.controller;

import com.stock.stocks.model.dto.StockDto;
import com.stock.stocks.model.request.SellRequest;
import com.stock.stocks.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService service;

    @GetMapping
    public List<StockDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/name")
    public StockDto findByName(@RequestParam(name = "name") String name){
        return service.findStockByName(name);
    }

    @GetMapping("/symbol")
    public StockDto findBySymbol(@RequestParam(name = "symbol") String symbol){
        return service.findStockBySymbol(symbol);
    }


    @PostMapping("/sell")
    public double sell(@RequestBody SellRequest request){
        return service.sellStock(request);
    }



}
