package com.jonasson.orderapi.client;

import com.jonasson.orderapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
@Component
public class CarClient {
    private final String CARS_MICROSERVICE_NAME = "cars";
    private WebClient client;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostConstruct
    private void init(){
        ServiceInstance service = discoveryClient.getInstances(CARS_MICROSERVICE_NAME).stream().findFirst().orElse(null);
        client = WebClient.create(service.getUri().toString() + "/api/v1/car/");
    }

    public Mono<Car[]> getAllCars() {
        return client.get().retrieve().bodyToMono(Car[].class);
    }
}
