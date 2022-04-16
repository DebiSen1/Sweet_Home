package com.upgrad.Booking.dao;

import com.upgrad.Booking.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository <BookingInfoEntity, Integer>{

}
