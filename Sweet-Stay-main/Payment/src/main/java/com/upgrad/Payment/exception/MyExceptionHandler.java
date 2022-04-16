package com.upgrad.Payment.exception;

import com.upgrad.Payment.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(InvalidPayment.class)
    public final ResponseEntity<ErrorDTO> handleInvalidPayment(InvalidPayment e, WebRequest res){

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Invalid Payment Id");
        errorDTO.setStatusCode(400);
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPaymentMode.class)
    public final ResponseEntity<ErrorDTO> handleInvalidPaymentMode(InvalidPaymentMode e, WebRequest res){

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Invalid mode of payment");
        errorDTO.setStatusCode(400);
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

}

