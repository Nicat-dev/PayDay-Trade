package com.customer.customers.mapper;

import com.customer.customers.model.client.Stock;
import com.customer.customers.model.dto.CustomerStockDto;
import com.customer.customers.model.entity.CustomerStock;
import com.customer.customers.model.request.CustomerStockRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerStockMapper {

    CustomerStockDto entityToDto(CustomerStock customerStock);
    List<CustomerStockDto> entityListToDtoList(List<CustomerStock> customerStocks);
    CustomerStock requestToEntity(CustomerStockRequest request);

    CustomerStock stockToCustomerStock(Stock stock);

}
