package com.customer.customers.service.impl;

import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.request.RegisterRequest;
import com.customer.customers.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto save(RegisterRequest request) {
        return null;
    }

    @Override
    public CustomerDto update(RegisterRequest request, Long id) {
        return null;
    }

    @Override
    public CustomerDto findById(Long id) {
        return null;
    }

    @Override
    public List<CustomerDto> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
