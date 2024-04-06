package com.stock.stocks.exception;

import com.stock.stocks.model.enums.Exceptions;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException{
    private final Exceptions exception;

    public ApplicationException(Exceptions exception){
        super(exception.getMessage());
        this.exception=exception;
    }

}