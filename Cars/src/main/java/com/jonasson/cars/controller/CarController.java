package com.jonasson.cars.controller;

import com.jonasson.cars.dto.CarDTO;
import com.jonasson.cars.entity.Car;
import com.jonasson.cars.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    @Autowired
    CarService carService;

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping("/")
    private List<CarDTO> getCars(){
        logger.info("Någon hämtade en lista med alla bilar");
        return carService.findCars();}

    @GetMapping("/{id}")
    private  CarDTO getCar(@PathVariable("id") Long id){
        logger.info("Någon hämtade bil med id " + id);
        return carService.findCar(id);
    }

    @PostMapping("/")
    private CarDTO postCar(@RequestBody Car car){
        logger.info("Någon postade en bil");
        return carService.postCar(car);
    }

    @PutMapping("/")
    private CarDTO updateCar(@RequestBody Car car){
        logger.info("Någon updaterade en bil");
        return carService.postCar(car);}

    @DeleteMapping("/{id}")
    private void deleteCar(@PathVariable("id") Long id){
        logger.info("Någon tog bort en bil");
        carService.deleteCar(id);}


}
