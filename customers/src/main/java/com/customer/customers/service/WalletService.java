package com.customer.customers.service;

import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.request.LoginRequest;

public interface WalletService {

    CustomerDto increaseBalance(double price, LoginRequest request);
    CustomerDto decreaseBalance(double price,LoginRequest request);

}
