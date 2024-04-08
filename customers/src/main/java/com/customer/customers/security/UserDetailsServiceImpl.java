package com.customer.customers.security;

import com.customer.customers.exception.ApplicationException;
import com.customer.customers.model.entity.Customer;
import com.customer.customers.model.enums.Exceptions;
import com.customer.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer foundUser = adminRepository.findByUsername(username)
                .orElseThrow(()-> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION));
        return new UserDetailImpl(foundUser);
    }
}
