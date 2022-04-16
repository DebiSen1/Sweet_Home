package com.upgrad.Payment.service;

import com.upgrad.Payment.entity.TransactionDetailsEntity;

public interface PaymentService {

    public TransactionDetailsEntity makeTransaction(TransactionDetailsEntity payment);

    public TransactionDetailsEntity getTransactionBasedOnId(int id);
}
