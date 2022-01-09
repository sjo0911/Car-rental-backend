package com.jonasson.orderapi.service;

import com.jonasson.orderapi.client.BookingClient;
import com.jonasson.orderapi.client.CarClient;

import com.jonasson.orderapi.model.AffectedBooking;
import com.jonasson.orderapi.model.Booking;
import com.jonasson.orderapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    BookingClient bookingClient;
    @Autowired
    CarClient carClient;

    public List<Car> getFreeCarsBetweenDates(String dateFrom, String dateTo)
    {
        List<Long> carIdsBooked = Arrays.asList(bookingClient.returnBookedCarsBetweenDates(dateFrom, dateTo).block());
        List<Car> allCars = Arrays.asList(carClient.getAllCars().block());
        List<Car> freeCars = new ArrayList<>();
        for(Car car: allCars){
            if(!carIdsBooked.contains(car.getId())){
                freeCars.add(car);
            }
        }
        return freeCars;
    }

    public List<Car> getFreeCarsToday(){
        return getFreeCarsBetweenDates(LocalDate.now().toString(), LocalDate.now().plusDays(1).toString());
    }

    public List<AffectedBooking> deleteCar(Long id){
        Car carToDelete = carClient.getCarById(id).block();
        List<Booking> bookingsAffected = Arrays.asList(bookingClient.getActiveBookingsWithCar(carToDelete.getId()).block());
        List<AffectedBooking> affectedBookings = new ArrayList<>();
        bookingsAffected.forEach(booking -> {
            if(booking.isActive()){
                AffectedBooking affectedBooking = new AffectedBooking();
                affectedBooking.setId(booking.getId());
                List<Car> freeCars = getFreeCarsBetweenDates(booking.getFromDate().toString(), booking.getToDate().toString());
                if(freeCars.size() == 0){
                    affectedBooking.setNewCarName("No free cars");
                    booking.setCarId((long) 0);
                } else {
                    List<Car> filteredCarsByModel = freeCars.stream()
                            .filter(car -> car.getModel().equals(carToDelete.getModel())).collect(Collectors.toList());
                    if(filteredCarsByModel.size() != 0){
                        booking.setCarId(filteredCarsByModel.get(0).getId());
                        affectedBooking.setNewCarName(filteredCarsByModel.get(0).getName());
                    } else {
                        booking.setCarId(freeCars.get(0).getId());
                        affectedBooking.setNewCarName(freeCars.get(0).getName());
                    }
                }
                bookingClient.updateBooking(booking).block();
                affectedBookings.add(affectedBooking);
            }
        });
        System.out.println("hej");
        carClient.deleteCarById(id);
        return affectedBookings;
    }


}
