package com.jonasson.booking.controller;

import com.jonasson.booking.entity.Booking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    BookingController bookingController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        assertNotNull(bookingController);
    }

    @Test
    @DisplayName("BOOKING REST can get all bookings")
    void getAll(){
        Booking[] bookings = testRestTemplate.getForObject("/api/v1/booking/", Booking[].class);
        assertEquals(3, bookings.length);
    }

    @Test
    @DisplayName("BOOKING REST can get booking by id 1 that should contain a customer id equal to 1")
    void getBYId(){
        Booking booking = testRestTemplate.getForObject("/api/v1/booking/1/", Booking.class);
        assertEquals(1, booking.getCustomerId());
    }

    @Test
    @DisplayName("BOOKING REST can post a new booking and get the posted booking in return")
    void post(){
        Booking booking = new Booking();
        booking.setCustomerId((long) 3);
        booking.setCarId((long) 3);
        booking.setFromDate(Date.valueOf("2024-12-01"));
        booking.setToDate(Date.valueOf("2024-12-12"));
        Booking postedBooking = testRestTemplate.postForObject("/api/v1/booking/", booking, Booking.class);
        assertEquals(booking.getFromDate().toString(), postedBooking.getFromDate().toString());
    }

    @Test
    @DisplayName("BOOKING REST gets a string reply(Car is already booked) when trying to post a booking with a car already booked")
    void postBookingForCarAlreadyBooked(){
        Booking booking = new Booking();
        booking.setCustomerId((long) 3);
        booking.setCarId((long) 1);
        booking.setId((long) 3);
        booking.setFromDate(Date.valueOf("2021-11-08"));
        booking.setToDate(Date.valueOf("2021-11-09"));
        String res = testRestTemplate.postForObject("/api/v1/booking/", booking, String.class);
        assertEquals("Car is already booked", res);
    }

    @Test
    @DisplayName("BOOKING REST gets a string reply that contains 'Car has not been selected' when trying to post a booking without car id")
    void postBookingWithoutCarId(){
        Booking booking = new Booking();
        booking.setCustomerId((long) 3);
        booking.setId((long) 3);
        booking.setFromDate(Date.valueOf("2021-11-08"));
        booking.setToDate(Date.valueOf("2021-11-09"));
        String res = testRestTemplate.postForObject("/api/v1/booking/", booking, String.class);
        assertTrue(res.contains("Car has not been selected"));
    }

    @Test
    @DisplayName("BOOKING REST can get carIds booked beetween dates")
    void getBeetweenDates(){
        Long[] carIds = testRestTemplate.getForObject("/api/v1/booking/bookings/2021-11-06/2021-11-14/", Long[].class);
        assertEquals(2, carIds.length);
    }
}