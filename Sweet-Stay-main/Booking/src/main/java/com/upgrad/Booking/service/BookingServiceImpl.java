package com.upgrad.Booking.service;

import com.upgrad.Booking.Config.KafkaConfig;
import com.upgrad.Booking.dao.BookingDao;
import com.upgrad.Booking.dto.BookingTransactionDTO;
import com.upgrad.Booking.entity.BookingInfoEntity;
import com.upgrad.Booking.exception.InvalidBooking;
import com.upgrad.Booking.exception.InvalidTransaction;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaConfig kafkaConfig;

    // generate random numbers based on provided total number count
    public static ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        return numberList;
    }

    @Override
    public BookingInfoEntity doBooking(BookingInfoEntity booking) {

        long timeDiff;
        if(booking.getFromDate().getTime() > booking.getToDate().getTime()) {
            timeDiff = booking.getFromDate().getTime() - booking.getToDate().getTime();
        } else {
            timeDiff = booking.getToDate().getTime() - booking.getFromDate().getTime();
        }

        // generate total number of days and calculate total price
        int noOfDays = (int) (timeDiff / (1000 * 60 * 60* 24));
        int roomPrice = 1000 * noOfDays * booking.getNumOfRooms();
        booking.setRoomPrice(roomPrice);

        String roomNumbers = String.join(",", getRandomNumbers(booking.getNumOfRooms()));
        booking.setRoomNumbers(roomNumbers);

        // generate current date as bookedOn date
        booking.setBookedOn(new Date());

        // store the data into database and return booking response
        return bookingDao.save(booking);
    }

    @Override
    public BookingInfoEntity getBookingById(int id) {
        // find booking from id into database raise error if id is not found
        if (bookingDao.findById(id).isPresent()){
            return bookingDao.findById(id).get();
        }
        throw new InvalidBooking( "Invalid Booking Id", 400 );
    }

    public BookingInfoEntity validateTransaction(int id, BookingTransactionDTO bookingTransactionDTO){

        // validate if paymentMode is not card or not upi then raise an error
        // return booking based on id from database if validation succeed
        if(bookingTransactionDTO.getPaymentMode() != null){
            String paymentMode = bookingTransactionDTO.getPaymentMode().toLowerCase().strip();
            if(!(paymentMode.equalsIgnoreCase("card") || paymentMode.equalsIgnoreCase("upi"))){
                throw new InvalidTransaction( "Invalid mode of payment", 400 );
            }
        } else {
            throw new InvalidTransaction( "Invalid mode of payment", 400 );
        }

        BookingInfoEntity booking = getBookingById(id);
        return booking;
    }

    @Override
    public BookingInfoEntity updateBookingAsTransaction(int id, BookingTransactionDTO bookingTransactionDTO) {
        // create transaction data into payment service
        BookingInfoEntity booking = validateTransaction(id, bookingTransactionDTO);

        Map<String,String> paymentUriMap = new HashMap<>();

        paymentUriMap.put("paymentMode", bookingTransactionDTO.getPaymentMode());
        paymentUriMap.put("bookingId", String.valueOf(bookingTransactionDTO.getBookingId()));
        paymentUriMap.put("upiId", bookingTransactionDTO.getUpiId());
        paymentUriMap.put("cardNumber", bookingTransactionDTO.getCardNumber());



        // calling payment service API using rest template
        BookingTransactionDTO updateBookingTransactionDTO = restTemplate.postForObject("http://PAYMENT-SERVICE/transaction", paymentUriMap,BookingTransactionDTO.class);

        System.out.println("BreakPoint: " + updateBookingTransactionDTO);
        // return booking data with updated information
        if(updateBookingTransactionDTO != null){
            booking.setTransactionID(updateBookingTransactionDTO.getTransactionId());
            bookingDao.save(booking);
            return booking;
        }
        return null;
    }

    @Override
    public void sendNotification(String topic, String key, String value) throws IOException {
        // send notification to topic using kafka
        Producer<String, String> producer = kafkaConfig.getProducer();
        System.out.println(producer.metrics());
        // send single message
        producer.send(new ProducerRecord<String, String>(topic, key, value));
        producer.close();
        System.out.println("Notification sent");

    }



}
