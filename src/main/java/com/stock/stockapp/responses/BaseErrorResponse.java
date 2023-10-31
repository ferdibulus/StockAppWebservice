package com.stock.stockapp.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@EqualsAndHashCode()
public class BaseErrorResponse extends RuntimeException{
    private final int errorCode;
    private final String errorMessage;

    public BaseErrorResponse(int errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
