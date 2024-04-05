package com.customer.customers.mapper;

import com.customer.customers.model.dto.CustomerStockDto;
import com.customer.customers.model.entity.CustomerStock;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerStockMapper {

    CustomerStockDto entityToDto(CustomerStock customerStock);
    List<CustomerStockDto> entityListToDtoList(List<CustomerStock> customerStocks);

}
