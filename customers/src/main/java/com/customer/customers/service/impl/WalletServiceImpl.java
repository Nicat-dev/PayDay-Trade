package com.customer.customers.service.impl;

import com.customer.customers.constant.Constants;
import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.model.entity.Customer;
import com.customer.customers.repository.CustomerRepository;
import com.customer.customers.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final CustomerRepository repository;


    @Override
    public ResponseDto increaseBalance(double price, Customer customer) {
        double currentWallet = customer.getWallet();
        customer.setWallet(currentWallet+price);
        repository.save(customer);
        return new ResponseDto(price + Constants.WALLET_INCREASE_SUCCESS_MESSAGE);
    }

    @Override
    public ResponseDto decreaseBalance(double price, Customer customer) {
        double currentWallet = customer.getWallet();
        customer.setWallet(currentWallet-price);
        repository.save(customer);
        return new ResponseDto(price + Constants.WALLET_DECREASE_SUCCESS_MESSAGE);
    }

    @Override
    public boolean checkBalance(double offer, Customer customer) {
        return customer.getWallet()>=offer;
    }
}
