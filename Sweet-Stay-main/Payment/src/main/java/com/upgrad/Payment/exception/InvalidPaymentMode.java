package com.upgrad.Payment.exception;

public class InvalidPaymentMode extends RuntimeException {

    private int statusCode;
    private static final long serialVersionUID = 1L;

    public InvalidPaymentMode(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
