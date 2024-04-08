package com.customer.customers.service.impl;

import com.customer.customers.exception.ApplicationException;
import com.customer.customers.exception.ResourceExistsException;
import com.customer.customers.exception.ResourceNotExistException;
import com.customer.customers.exception.ResourceNotFoundException;
import com.customer.customers.mapper.CustomerMapper;
import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.model.entity.Customer;
import com.customer.customers.model.enums.Exceptions;
import com.customer.customers.model.request.DepositRequest;
import com.customer.customers.model.request.RegisterRequest;
import com.customer.customers.repository.CustomerRepository;
import com.customer.customers.service.AuthControlService;
import com.customer.customers.service.CustomerService;
import com.customer.customers.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final AuthControlService authControlService;
    private final WalletService walletService;


    @Override
    public CustomerDto save(RegisterRequest request) {
        ifExist(request.username());
        Customer customer = mapper.registerToEntity(request);
        customer.setPassword(authControlService.encode(request.password()));
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
                .password(authControlService.encode(request.password()))
                .build();
        return mapper.entityToDto(customer);
    }

    @Override
    public CustomerDto findById(Long id) {
        return mapper.entityToDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id)));
    }

    @Override
    public CustomerDto findByUsername(String username) {
        return mapper.entityToDto(getByUsername(username));
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
        if (authControlService.authControlService(request.username(), request.password())){
            walletService.increaseBalance(request.money(), getByUsername(request.username()));
            return new ResponseDto(request.money() + " was added to " + request.username() + " balance");
        }else{
            throw new ApplicationException(Exceptions.PASSWORD_DOESNT_MATCH);
        }
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

    private Customer getByUsername(String username){
        return repository.findByUsername(username)
                .orElseThrow(()->new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION));
    }

}
