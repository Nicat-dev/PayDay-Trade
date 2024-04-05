package com.customer.customers.service.impl;

import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.request.LoginRequest;
import com.customer.customers.service.WalletService;

public class WalletServiceImpl implements WalletService {
    @Override
    public CustomerDto increaseBalance(double price, LoginRequest request) {
        return null;
    }

    @Override
    public CustomerDto decreaseBalance(double price, LoginRequest request) {
        return null;
    }
}
