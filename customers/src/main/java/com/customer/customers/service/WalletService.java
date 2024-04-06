package com.customer.customers.service;

import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.model.entity.Customer;

public interface WalletService {

    ResponseDto increaseBalance(double price, Customer customer);

    ResponseDto decreaseBalance(double price, Customer customer);

    boolean checkBalance(double offer, Customer customer);

}
