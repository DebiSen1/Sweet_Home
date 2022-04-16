/**
 * DTO class which is used to map exception or error if any
 * Author : @Debi
 * */

package com.upgrad.Payment.dto;


public class ErrorDTO {

    private String message;

    private int statusCode ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "message=" + message +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }

}