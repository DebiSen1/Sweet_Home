package com.upgrad.Payment.service;

import com.upgrad.Payment.Dao.PaymentDao;
import com.upgrad.Payment.entity.TransactionDetailsEntity;
import com.upgrad.Payment.exception.InvalidPayment;
import com.upgrad.Payment.exception.InvalidPaymentMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;


    @Override
    public TransactionDetailsEntity makeTransaction(TransactionDetailsEntity payment) {

        // validate if paymentMode is not card or not upi then raise an error
        // return payment based on id from database if validation succeed and stores into the database

        if(payment.getPaymentMode() != null){

            String paymentMode = payment.getPaymentMode().toLowerCase().strip();

            if(!(paymentMode.equalsIgnoreCase("card") || paymentMode.equalsIgnoreCase("upi"))){
                throw new InvalidPaymentMode( "Invalid mode of payment", 400 );
            }
        } else {
            throw new InvalidPaymentMode( "Invalid mode of payment", 400 );
        }

        return paymentDao.save(payment);
    }

    @Override
    public TransactionDetailsEntity getTransactionBasedOnId(int id) {

        // find payment/transaction from id into database raise error if id is not found

        if (paymentDao.findById(id).isPresent()){

            return paymentDao.findById(id).get();
        }

        throw new InvalidPayment( "Invalid Payment Id", 400 );

    }
}
