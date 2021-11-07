package com.jonasson.cars.service;

import com.jonasson.cars.entity.Car;
import com.jonasson.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> findCars(){
        return carRepository.findAll();
    }

    public Car findCar(Long id){return carRepository.getById(id);}

    public Car postCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }
}
