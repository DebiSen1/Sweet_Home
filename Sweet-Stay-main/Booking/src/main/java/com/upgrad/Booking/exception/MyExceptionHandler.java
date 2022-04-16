package com.upgrad.Booking.exception;

import com.upgrad.Booking.exception.InvalidTransaction;
import com.upgrad.Booking.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(InvalidTransaction.class)
    public final ResponseEntity <ErrorDTO> handleInvalidTransaction(InvalidTransaction e, WebRequest res){

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Invalid mode of payment");
        errorDTO.setStatusCode(400);
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBooking.class)
    public final ResponseEntity<ErrorDTO> handleInvalidBooking(InvalidBooking e, WebRequest res){

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Invalid Booking Id");
        errorDTO.setStatusCode(400);
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
