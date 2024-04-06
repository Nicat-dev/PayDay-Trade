package com.customer.customers.service;

import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.model.request.DepositRequest;
import com.customer.customers.model.request.RegisterRequest;

import java.util.List;

public interface CustomerService {

    CustomerDto save(RegisterRequest request);

    CustomerDto update(RegisterRequest request, Long id);

    CustomerDto findById(Long id);

    List<CustomerDto> findAll();

    void delete(Long id);

    ResponseDto depositMoney(DepositRequest request);

}
