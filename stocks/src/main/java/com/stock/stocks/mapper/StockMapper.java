package com.stock.stocks.mapper;

import com.stock.stocks.model.dto.StockDto;
import com.stock.stocks.model.entity.Stock;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDto entityToDto(Stock stock);
    List<StockDto> entityListToDtoList(List<Stock> stockList);

}
