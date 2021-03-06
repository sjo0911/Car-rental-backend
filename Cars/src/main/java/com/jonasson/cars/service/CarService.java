package com.jonasson.cars.service;

import com.jonasson.cars.dto.CarDTO;
import com.jonasson.cars.entity.Car;
import com.jonasson.cars.helpers.Mapper;
import com.jonasson.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> findCars(){
        return Mapper.toCarDTOS(carRepository.getActiveCars());
    }

    public CarDTO findCar(Long id){return Mapper.toCarDTO(carRepository.getById(id));}

    public CarDTO postCar(CarDTO car){
        return Mapper.toCarDTO(carRepository.save(Mapper.toCar(car)));
    }

    public CarDTO deleteCar(Long id){
        Car carToDelete = carRepository.getById(id);
        carToDelete.setActive(false);
        return Mapper.toCarDTO(carRepository.save(carToDelete));
    }
}
