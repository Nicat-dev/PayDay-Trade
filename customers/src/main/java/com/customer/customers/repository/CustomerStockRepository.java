package com.customer.customers.repository;

import com.customer.customers.model.entity.CustomerStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerStockRepository extends JpaRepository<CustomerStock, Long> {

    CustomerStock findCustomerStockBySymbol(String symbol);

}
