package com.jonasson.booking.service;

import com.jonasson.booking.dto.BookingDto;
import com.jonasson.booking.entity.Booking;
import com.jonasson.booking.exception.AlreadyBookedException;
import com.jonasson.booking.helpers.EntityVoMapper;
import com.jonasson.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingDto> findBookings(){return EntityVoMapper.bookingEntityListToDtoList(bookingRepository.findAll());}

    public BookingDto findBookingsById(Long id){return EntityVoMapper.bookingEntityToDto(bookingRepository.getById(id));}

    public BookingDto postUpdateBooking(BookingDto booking) throws AlreadyBookedException, ValidationException {
        booking.setActive(true);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<BookingDto>> violations = validator.validate(booking);
        if(violations.size() != 0){
            throw new ValidationException(violations.toArray()[0].toString());
        }
        if(bookingRepository.checkIfCarIsBooked(booking.getFromDate(), booking.getToDate(), booking.getCarId()).size() >= 1){
            throw new AlreadyBookedException("Car is already booked");
        } else {
            return EntityVoMapper.bookingEntityToDto(bookingRepository.save(EntityVoMapper.bookingDtoToEntity(booking)));
        }

    }

    public BookingDto updateBooking(BookingDto booking) throws AlreadyBookedException, ValidationException {
        booking.setActive(true);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<BookingDto>> violations = validator.validate(booking);
        if(violations.size() != 0){
            throw new ValidationException(violations.toArray()[0].toString());
        }
        if(bookingRepository.checkIfCarIsBookedUpdateBooking(booking.getFromDate(), booking.getToDate(), booking.getCarId(), booking.getId()).size() >= 1){
            throw new AlreadyBookedException("Car is already booked");
        } else {
            return EntityVoMapper.bookingEntityToDto(bookingRepository.save(EntityVoMapper.bookingDtoToEntity(booking)));
        }
    }

    public List<BookingDto> getCustomerBookings(Long id){
        return EntityVoMapper.bookingEntityListToDtoList(bookingRepository.getCustomersBookings(id));
    }

    public void deleteBooking(Long id){
        Booking booking = bookingRepository.getById(id);
        booking.setActive(false);
        bookingRepository.save(booking);
    }

    public List<Long> getAllCarIdsBookedBetweenDates(Date fromDate, Date toDate){
        return bookingRepository.getAllCarIdsBookedBetweenDates(fromDate,toDate);
    }

    public List<BookingDto> getActiveBookingsWithCar(Long id){
        return EntityVoMapper.bookingEntityListToDtoList(bookingRepository.getActiveBookingsWithCar(id));
    }
}
