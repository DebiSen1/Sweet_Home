package com.upgrad.Booking.service;


import com.upgrad.Booking.dto.BookingTransactionDTO;
import com.upgrad.Booking.entity.BookingInfoEntity;

import java.io.IOException;

public interface BookingService {

    public BookingInfoEntity doBooking(BookingInfoEntity bookingInfoEntity);
    public  BookingInfoEntity getBookingById(int Id);
    public BookingInfoEntity updateBookingAsTransaction(int Id, BookingTransactionDTO bookingTransactionDTO);
    public void sendNotification(String topic, String key, String value) throws IOException;

}
