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

    public void deleteBooking(Long id){bookingRepository.deleteById(id);}

    public List<Long> getAllCarIdsBookedBetweenDates(Date fromDate, Date toDate){
        return bookingRepository.getAllCarIdsBookedBetweenDates(fromDate,toDate);
    }
}
