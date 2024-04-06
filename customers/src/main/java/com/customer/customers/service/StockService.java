package com.customer.customers.service;

import com.customer.customers.model.dto.BuySellRequestDto;
import com.customer.customers.model.dto.ResponseDto;

public interface StockService {

    ResponseDto buyStock(BuySellRequestDto requestDto);

    ResponseDto sellStock(BuySellRequestDto requestDto);

}
