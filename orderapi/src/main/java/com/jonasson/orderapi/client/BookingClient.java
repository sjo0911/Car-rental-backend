package com.jonasson.orderapi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
@Component
public class BookingClient {
    private final String BOOKING_MICROSERVICE_NAME = "booking";
    private WebClient client;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostConstruct
    private void init(){
        ServiceInstance service = discoveryClient.getInstances(BOOKING_MICROSERVICE_NAME).stream().findFirst().orElse(null);
        client = WebClient.create(service.getUri().toString() + "/api/v1/booking");
    }

    public Mono<Long[]> returnBookedCarsBetweenDates(String dateFrom, String dateTo) {
        return client.get().uri("/bookings/" + dateFrom + "/" + dateTo).retrieve().bodyToMono(Long[].class);
    }
}
