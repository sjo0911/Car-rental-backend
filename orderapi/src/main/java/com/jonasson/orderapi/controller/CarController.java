package com.jonasson.orderapi.controller;

import com.jonasson.orderapi.model.AffectedBooking;
import com.jonasson.orderapi.model.Car;
import com.jonasson.orderapi.model.Customer;
import com.jonasson.orderapi.service.CarService;
import com.jonasson.orderapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    CustomerService customerService;


    @GetMapping("/cars/{fromDate}/{toDate}")
    private List<Car> getFreeCarsBetweenDates(@PathVariable("fromDate") String fromDateString, @PathVariable("toDate") String toDateString){
        return carService.getFreeCarsBetweenDates(fromDateString, toDateString);
    }

    @GetMapping("/cars")
    private List<Car> getFreeCarsToday(){return carService.getFreeCarsToday();}

    @GetMapping("/customers")
    private List<Customer> getCustomersWithNumOfBookings(){return customerService.getCustomersWithNumberOfBookings();}

    @DeleteMapping("/cars/{id}")
    private List<AffectedBooking> deleteCar(@PathVariable("id") Long id){
        return carService.deleteCar(id);
    }
}
