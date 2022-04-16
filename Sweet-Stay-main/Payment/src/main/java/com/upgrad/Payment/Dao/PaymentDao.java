/**
 * Data Access layer for accessing and interacting with the database
 * for transaction purpose
 *
 * Author : @Debi
 * */
package com.upgrad.Payment.Dao;

import com.upgrad.Payment.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository <TransactionDetailsEntity, Integer>{
}
