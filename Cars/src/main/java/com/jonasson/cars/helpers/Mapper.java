package com.jonasson.cars.helpers;

import com.jonasson.cars.dto.CarDTO;
import com.jonasson.cars.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static CarDTO toCarDTO(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setModel(car.getModel());
        carDTO.setName(car.getName());
        carDTO.setPricePerDay(car.getPricePerDay());
        return carDTO;
    }

    public static List<CarDTO> toCarDTOS(List<Car> cars){
        List<CarDTO> carDTOS = new ArrayList<>();
        cars.forEach(car -> carDTOS.add(toCarDTO(car)));
        return carDTOS;
    }
}
