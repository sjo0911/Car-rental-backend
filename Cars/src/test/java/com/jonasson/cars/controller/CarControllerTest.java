package com.jonasson.cars.controller;

import com.jonasson.cars.dto.CarDTO;
import com.jonasson.cars.entity.Car;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerTest {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    CarController carController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        assertNotNull(carController);
    }

    @Test
    @Order(1)
    @DisplayName("CAR REST can get all cars")
    void getAll(){
        CarDTO[] carDTOS = testRestTemplate.getForObject("/api/v1/car/", CarDTO[].class);
        assertEquals(2, carDTOS.length);
    }

    @Test
    @Order(2)
    @DisplayName("CAR REST can get car by id 1 that should contain a car with id equal to 1")
    void getBYId(){
        CarDTO carDTO = testRestTemplate.getForObject("/api/v1/car/1/", CarDTO.class);
        assertEquals(1, carDTO.getId());
    }

    @Test
    @Order(3)
    @DisplayName("Car REST can post a new car and get the posted car in return")
    void post(){
        Car car = new Car();
        car.setModel("Testla");
        car.setName("Ny tesla");
        car.setPricePerDay(250);
        Car postedCar = testRestTemplate.postForObject("/api/v1/car/", car, Car.class);
        assertEquals(car.getName(), postedCar.getName());
    }

    @Test
    @Order(4)
    @DisplayName("CAR REST can delete 1")
    void delete(){
        CarDTO[] carDTOSBeforeDelete = testRestTemplate.getForObject("/api/v1/car/", CarDTO[].class);
        int numberOfCarsBefore = carDTOSBeforeDelete.length;
        testRestTemplate.delete("/api/v1/car/2/");
        CarDTO[] carDTOSAfterDelete = testRestTemplate.getForObject("/api/v1/car/", CarDTO[].class);
        int numberOfCarsAfter = carDTOSAfterDelete.length;
        assertEquals(numberOfCarsBefore -1, numberOfCarsAfter);
    }
}