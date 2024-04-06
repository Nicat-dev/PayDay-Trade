package com.customer.customers.service.impl;

import com.customer.customers.exception.ApplicationException;
import com.customer.customers.model.enums.Exceptions;
import com.customer.customers.repository.CustomerRepository;
import com.customer.customers.service.AuthControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class
AuthControlServiceImpl implements AuthControlService {

    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public boolean authControlService(String username, String password) {
        if (repository.existsByUsername(username)){
            if (encoder.matches(repository.findByUsername(username).getPassword(),password)){
                return true;
            }else{
                throw new ApplicationException(Exceptions.PASSWORD_DOESNT_MATCH);
            }
        }else {
            throw new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION);
        }
    }
}
