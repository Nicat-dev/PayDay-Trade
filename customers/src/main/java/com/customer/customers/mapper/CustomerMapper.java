package com.customer.customers.mapper;

import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerDto entityToDto(Customer customer);
    List<CustomerDto> entityListToDtoList(List<Customer> customers);


}
