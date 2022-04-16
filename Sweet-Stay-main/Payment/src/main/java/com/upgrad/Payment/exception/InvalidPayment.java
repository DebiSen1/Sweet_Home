package com.upgrad.Payment.exception;

public class InvalidPayment extends RuntimeException {

    private int statusCode;
    private static final long serialVersionUID = 1L;

    public InvalidPayment(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
