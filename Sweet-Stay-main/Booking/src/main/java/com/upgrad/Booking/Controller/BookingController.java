package com.upgrad.Booking.Controller;

import com.upgrad.Booking.dto.BookingDTO;
import com.upgrad.Booking.dto.BookingTransactionDTO;
import com.upgrad.Booking.entity.BookingInfoEntity;
import com.upgrad.Booking.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class BookingController {

    // bookingService instance is used to invoke booking related methods
    @Autowired
    private BookingService bookingService ;

    @Autowired
    ModelMapper modelMapper;

    // POST API to create booking and store into Database
    // It will return booking detail data as a response
    @PostMapping(value="/booking", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBooking(@RequestBody BookingDTO bookingDTO){

        BookingInfoEntity booking = modelMapper.map(bookingDTO, BookingInfoEntity.class);
        System.out.println("BookingDTO: "+ bookingDTO);
        System.out.println("Booking: "+ booking);
        BookingInfoEntity savedBooking = bookingService.doBooking(booking);
        BookingDTO savedbookingDTO = modelMapper.map(savedBooking, BookingDTO.class);
        System.out.println("Saved Booking: " + savedBooking);
        System.out.println("Saved Booking DTO: " + savedBooking);
        return new ResponseEntity(savedbookingDTO, HttpStatus.CREATED);
    }

    // POST API to confirm booking and store into Database as a booking transaction
    // It will return booking transaction detail data as a response
    @PostMapping(value="/booking/{bookingId}/transaction", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity confirmBooking(
            @PathVariable(name = "bookingId")  int bookingId,
            @RequestBody BookingTransactionDTO bookingTransactionDTO) throws IOException {

        BookingInfoEntity savedBooking = bookingService.updateBookingAsTransaction(bookingId, bookingTransactionDTO);
        String message = "Booking confirmed for user with aadhaar number: " + savedBooking.getAadharNumber() +    "    |    "  + "Here are the booking details:    " + savedBooking.toString();

        // kafka notification send after successful transaction
        bookingService.sendNotification("message", "notification", message);

        return new ResponseEntity(savedBooking, HttpStatus.CREATED);
    }

}
