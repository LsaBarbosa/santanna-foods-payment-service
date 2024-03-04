package com.santanna.payment.infra;

public class ValidationException extends RuntimeException{
    public ValidationException(String msg){
        super(msg);
    }
}
