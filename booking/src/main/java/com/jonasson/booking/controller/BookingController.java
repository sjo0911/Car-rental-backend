package com.jonasson.booking.controller;

import com.jonasson.booking.dto.BookingDto;
import com.jonasson.booking.exception.AlreadyBookedException;
import com.jonasson.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping("/")
    private List<BookingDto> getBookings(){return bookingService.findBookings();}

    //TODO fixa en mer initiv address för att hämta id:s
    @GetMapping("/bookings/{fromDate}/{toDate}")
    private List<Long> getCaridsBookedBetweenDates(@PathVariable("fromDate") String fromDateString, @PathVariable("toDate") String toDateString){
        Date fromDate = Date.valueOf(fromDateString);
        Date toDate = Date.valueOf(toDateString);
        return bookingService.getAllCarIdsBookedBetweenDates(fromDate, toDate);
    }

    @GetMapping("/{id}")
    private  BookingDto getBooking(@PathVariable("id") Long id){return bookingService.findBookingsById(id);}

    @PostMapping("/")
    private ResponseEntity postBooking(@RequestBody BookingDto booking){
        try {
            return new ResponseEntity<BookingDto>(bookingService.postUpdateBooking(booking), HttpStatus.CREATED);
        } catch (AlreadyBookedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    private ResponseEntity updateBooking(@RequestBody BookingDto booking){
        try {
            return new ResponseEntity<BookingDto>(bookingService.postUpdateBooking(booking), HttpStatus.CREATED);
        } catch (AlreadyBookedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private void deleteCustomer(@PathVariable("id") Long id){bookingService.deleteBooking(id);}
}
