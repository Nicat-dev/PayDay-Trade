package com.customer.customers.service.impl;

import com.customer.customers.exception.ResourceExistsException;
import com.customer.customers.exception.ResourceNotExistException;
import com.customer.customers.exception.ResourceNotFoundException;
import com.customer.customers.mapper.CustomerMapper;
import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.model.entity.Customer;
import com.customer.customers.model.request.DepositRequest;
import com.customer.customers.model.request.RegisterRequest;
import com.customer.customers.repository.CustomerRepository;
import com.customer.customers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public CustomerDto save(RegisterRequest request) {
        ifExist(request.username());
        Customer customer = mapper.registerToEntity(request);
        customer.setPassword(passwordEncoder.encode(request.password()));
        return mapper.entityToDto(repository.save(customer));
    }

    @Override
    public CustomerDto update(RegisterRequest request, Long id) {
        ifNotExist(id);
        Customer customer = Customer.builder()
                .id(id)
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        return mapper.entityToDto(customer);
    }

    @Override
    public CustomerDto findById(Long id) {
        return mapper.entityToDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id)));
    }

    @Override
    public List<CustomerDto> findAll() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    @Override
    public void delete(Long id) {
        ifNotExist(id);
        repository.deleteById(id);
    }

    @Override
    public ResponseDto depositMoney(DepositRequest request) {
        return null;
    }

    private void ifExist(String username) {
        if (repository.existsByUsername(username)) {
            throw new ResourceExistsException("username", "username", username);
        }
    }

    private void ifNotExist(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotExistException("Customer", "id", id);
        }
    }

}
