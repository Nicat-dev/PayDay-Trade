package com.customer.customers.repository;

import com.customer.customers.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByUsername(String username);
    boolean existsById(Long id);
    Customer findByUsername(String username);
}
