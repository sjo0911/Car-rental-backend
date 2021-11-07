package com.jonasson.orderapi.service;

import com.jonasson.orderapi.client.BookingClient;
import com.jonasson.orderapi.client.CarClient;
import com.jonasson.orderapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
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

}
