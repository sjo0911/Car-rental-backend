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
public class CustomerClient {
    private final String CUSTOMER_MICROSERVICE_NAME = "customer";
    private WebClient client;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostConstruct
    private void init(){
        ServiceInstance service = discoveryClient.getInstances(CUSTOMER_MICROSERVICE_NAME).stream().findFirst().orElse(null);
        client = WebClient.create(service.getUri().toString() + "/api/v1/customer/");
    }

    public Mono<Car[]> getCustomer(Long customerId) {
        return client.get().uri("/" +customerId).retrieve().bodyToMono(Car[].class);
    }
}
