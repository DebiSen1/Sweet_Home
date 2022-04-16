package com.upgrad.Payment;

import com.upgrad.Payment.dto.TransactionDTO;
import com.upgrad.Payment.entity.TransactionDetailsEntity;

public class Converter {

    public static TransactionDTO covertPaymentEntityToDTO(TransactionDetailsEntity payment) {
        // converts payment instance to paymentDTO instance
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setPaymentMode(payment.getPaymentMode());
        transactionDTO.setBookingId(payment.getBookingId());
        transactionDTO.setCardNumber(payment.getCardNumber());
        transactionDTO.setTransactionId(payment.getTransactionId());
        transactionDTO.setUpiId(payment.getUpiId());

        return transactionDTO;
    }

    public static TransactionDetailsEntity covertPaymentDTOToEntity(TransactionDTO transactionDTO) {
        // converts paymentDTO instance to payment instance
        TransactionDetailsEntity payment = new TransactionDetailsEntity();

        payment.setPaymentMode(transactionDTO.getPaymentMode());
        payment.setBookingId(transactionDTO.getBookingId());
        payment.setCardNumber(transactionDTO.getCardNumber());
        payment.setTransactionId(transactionDTO.getTransactionId());
        payment.setUpiId(transactionDTO.getUpiId());

        return payment;
    }

}
