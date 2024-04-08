package com.customer.customers.service.impl;

import com.customer.customers.exception.ApplicationException;
import com.customer.customers.mapper.CustomerStockMapper;
import com.customer.customers.model.client.Stock;
import com.customer.customers.model.dto.BuySellRequestDto;
import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.model.dto.SellRequestDto;
import com.customer.customers.model.entity.Customer;
import com.customer.customers.model.entity.CustomerStock;
import com.customer.customers.model.enums.Exceptions;
import com.customer.customers.repository.CustomerRepository;
import com.customer.customers.repository.CustomerStockRepository;
import com.customer.customers.service.StockClient;
import com.customer.customers.service.StockService;
import com.customer.customers.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final CustomerStockRepository repository;
    private final WalletService walletService;
    private final StockClient client;
    private final CustomerRepository customerRepository;
    private final CustomerStockMapper mapper;

    @Override
    public ResponseDto buyStock(BuySellRequestDto requestDto) {
        Optional<Customer> customer = customerRepository.findByUsername(requestDto.getUsername());
        CustomerStock customerStockBySymbol = repository
                .findCustomerStockBySymbol(requestDto.getSymbol());

        SellRequestDto request = SellRequestDto.builder()
                .symbol(requestDto.getSymbol())
                .offerPrice(requestDto.getOffer())
                .build();

        double price = client.sellStock(request);

        if (price == 0) {
            if (walletService.checkBalance(request.getOfferPrice(),
                    customer.orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)))) {
                if (customerStockBySymbol !=null){
                    saveAlreadyHaveStock(customerStockBySymbol,request.getOfferPrice(),customer,true);
                    walletService.decreaseBalance(request.getOfferPrice(),customer.orElseThrow());
                }else{
                    saveStock(requestDto.getSymbol(), request.getOfferPrice(), customer,true);
                    walletService.decreaseBalance(request.getOfferPrice(),customer.orElseThrow());
                }
                return new ResponseDto("Buying is successfully");
            }else{
                throw new ApplicationException(Exceptions.WALLET_NOT_ENOUGH_EXCEPTION);
            }
        }else{
            saveStock(request.getSymbol(), request.getOfferPrice(), customer,false);
            throw new ApplicationException(Exceptions.HIGH_PRICE_EXCEPTION);
        }

    }

    @Override
    public ResponseDto sellStock(BuySellRequestDto buySellRequestDto) {

        Optional<Customer> customer = customerRepository.findByUsername(buySellRequestDto.getUsername());
        CustomerStock stock = repository.findCustomerStockBySymbol(buySellRequestDto.getSymbol());
        if (stock == null){
            throw new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION);
        }

        if (stock.isBuyStatus() && !stock.isSellStatus()){
            SellRequestDto request = SellRequestDto.builder()
                    .symbol(buySellRequestDto.getSymbol())
                    .offerPrice(buySellRequestDto.getOffer())
                    .build();
            double price = client.sellStock(request);

            if (price != 0){
                if (walletService.checkBalance(buySellRequestDto.getOffer(),
                        customer.orElseThrow(()-> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)))){
                    stock.setSellStatus(true);
                    stock.setSellRequest(false);
                    stock.setSellPrice(price);
                    repository.save(stock);
                    walletService.increaseBalance(price,customer
                            .orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)));
                    return new ResponseDto("Sell is successfully." +
                            buySellRequestDto.getUsername() + " gain " + price +" dollar");
                }else{
                    throw new ApplicationException(Exceptions.WALLET_NOT_ENOUGH_EXCEPTION);
                }
            }else{
                stock.setSellRequest(true);
                stock.setSellPrice(buySellRequestDto.getOffer());
                repository.save(stock);
                return new ResponseDto("The stock price is higher than your bid("+request.getOfferPrice()+"). However, " +
                        "we have tracked your stock(" + request.getSymbol() + "). If the stock's value falls below your bid, it will be taken automatically.");
            }

        }else{
            throw new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION);
        }
    }


    private void saveStock(String symbol, double offerPrice, Optional<Customer> customer, boolean buyStatus) {
        CustomerStock customerStock = saveCustomerStock(symbol, customer, offerPrice, buyStatus);
        List<CustomerStock> customerStockList = customer
                .orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)).getCustomerStocks();
        customerStockList.add(customerStock);
        customerRepository.save(customer.orElseThrow());

    }

    private void saveAlreadyHaveStock(CustomerStock stock, double price,
                                      Optional<Customer> customer, boolean buyStatus) {
        CustomerStock customerStock = getAlreadyHaveStock(stock, customer, price, buyStatus);
        List<CustomerStock> customerStocks = customer
                .orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)).getCustomerStocks();
        customerStocks.add(customerStock);
        customerRepository.save(customer.orElseThrow());
    }

    private CustomerStock getAlreadyHaveStock(CustomerStock customerStock, Optional<Customer> customer,
                                              double offer, boolean buyStatus) {
        customerStock.setBuyStatus(buyStatus);
        customerStock.setBuyPrice(offer);
        customerStock.setCustomer(customer.orElseThrow());
        repository.save(customerStock);
        return customerStock;
    }

    private CustomerStock saveCustomerStock(String symbol, Optional<Customer> customer,
                                            double offer, boolean buyStatus) {
        Stock stock = client.findStockBySymbol(symbol);
        if (stock != null) {
            CustomerStock customerStock = mapper.stockToCustomerStock(stock);
            customerStock.setCustomer(customer
                    .orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)));
            customerStock.setBuyPrice(offer);
            customerStock.setBuyStatus(buyStatus);

            return repository.save(customerStock);
        } else {
            throw new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION);
        }
    }
}
