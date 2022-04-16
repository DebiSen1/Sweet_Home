package com.upgrad.Booking.exception;

public class InvalidBooking extends RuntimeException {

    private int statusCode;
    private static final long serialVersionUID = 1L;

    public InvalidBooking(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
