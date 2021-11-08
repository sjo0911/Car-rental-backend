package com.jonasson.booking.helpers;

import com.jonasson.booking.dto.BookingDto;
import com.jonasson.booking.entity.Booking;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EntityVoMapper {
    public static BookingDto bookingEntityToDto(Booking booking){
        BookingDto bookingDto = new BookingDto();
        bookingDto.setCarId(booking.getCarId());
        bookingDto.setCustomerId(booking.getCustomerId());
        Date fromDate = Date.valueOf(booking.getFromDate().toString());
        Date toDate = Date.valueOf(booking.getToDate().toString());
        bookingDto.setFromDate(fromDate);
        bookingDto.setToDate(toDate);
        bookingDto.setActive(booking.isActive());
        if(booking.getId() != null)
            bookingDto.setId(booking.getId());
        return bookingDto;
    }

    public static Booking bookingDtoToEntity(BookingDto bookingDto){
        Booking booking = new Booking();
        booking.setCarId(bookingDto.getCarId());
        booking.setCustomerId(bookingDto.getCustomerId());
        Date fromDate = Date.valueOf(bookingDto.getFromDate().toString());
        Date toDate = Date.valueOf(bookingDto.getToDate().toString());
        booking.setFromDate(fromDate);
        booking.setToDate(toDate);
        booking.setActive(bookingDto.isActive());
        if(bookingDto.getId() != null)
            booking.setId(bookingDto.getId());
        return booking;
    }

    public static List<BookingDto> bookingEntityListToDtoList(List<Booking> bookings){
        List<BookingDto> bookingDtos = new ArrayList<>();
        bookings.forEach(booking -> bookingDtos.add(bookingEntityToDto(booking)));
        return bookingDtos;
    }
}
