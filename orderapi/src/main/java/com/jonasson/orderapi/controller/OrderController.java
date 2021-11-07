package com.jonasson.orderapi.controller;

import com.jonasson.orderapi.model.Car;
import com.jonasson.orderapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/cars/{fromDate}/{toDate}")
    private List<Car> getFreeCarsBetweenDates(@PathVariable("fromDate") String fromDateString, @PathVariable("toDate") String toDateString){
        return orderService.getFreeCarsBetweenDates(fromDateString, toDateString);
    }

    @GetMapping("/cars")
    private List<Car> getFreeCarsToday(){return orderService.getFreeCarsToday();}
}
