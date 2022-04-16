package com.upgrad.Booking.exception;

public class InvalidTransaction extends RuntimeException {

    private int statusCode;
    private static final long serialVersionUID = 1L;

    public InvalidTransaction(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}