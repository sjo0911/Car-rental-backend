package com.jonasson.cars.controller;

import com.jonasson.cars.entity.Car;
import com.jonasson.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/")
    private List<Car> getCars(){return carService.findCars();}

    @GetMapping("/{id}")
    private  Car getCar(@PathVariable("id") Long id){return carService.findCar(id);}

    @PostMapping("/")
    private Car postCar(@RequestBody Car car){return carService.postCar(car);}

    @PutMapping("/")
    private Car updateCar(@RequestBody Car car){return carService.postCar(car);}

    @DeleteMapping("/{id}")
    private void deleteCar(@PathVariable("id") Long id){carService.deleteCar(id);}
}
