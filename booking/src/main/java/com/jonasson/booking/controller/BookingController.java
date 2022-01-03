package com.jonasson.booking.controller;

import com.jonasson.booking.dto.BookingDto;
import com.jonasson.booking.exception.AlreadyBookedException;
import com.jonasson.booking.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    //TODO fixa en mer initiv address för att hämta id:s
    @GetMapping("/bookings/{fromDate}/{toDate}")
    private List<Long> getCaridsBookedBetweenDates(@PathVariable("fromDate") String fromDateString, @PathVariable("toDate") String toDateString){
        logger.info("Någon anropade getCarIdsBookedBetweenDates");
        Date fromDate = Date.valueOf(fromDateString);
        Date toDate = Date.valueOf(toDateString);
        return bookingService.getAllCarIdsBookedBetweenDates(fromDate, toDate);
    }

    @GetMapping("/{id}")
    private  BookingDto getBooking(@PathVariable("id") Long id){
        logger.info("Någon hämtade en bokning med id " + id);
        return bookingService.findBookingsById(id);
    }

    @GetMapping("/customersbooking/{id}")
    private  List<BookingDto> getCustomersBookings(@PathVariable("id") Long customerId){
        logger.info("En kund hämtade sina bokningar");
        return bookingService.getCustomerBookings(customerId);
    }

    @GetMapping("/carsactivebookings/{id}")
    private  List<BookingDto> getCarsActiveBookings(@PathVariable("id") Long carId){
        logger.info("Någon hämtade alla aktiva bokningar på en bil");
        return bookingService.getActiveBookingsWithCar(carId);
    }

    @PostMapping("/")
    private ResponseEntity postBooking(@RequestBody BookingDto booking){
        logger.info("Någon skapade en bokning");
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
        logger.info("Någon ändrade en bokning");
        try {
            return new ResponseEntity<BookingDto>(bookingService.postUpdateBooking(booking), HttpStatus.CREATED);
        } catch (AlreadyBookedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cancelorder")
    private void deleteBooking(@RequestBody BookingDto booking){
        logger.info("Någon tog bort en bokning");
        bookingService.deleteBooking(booking.getId());
    }


}
