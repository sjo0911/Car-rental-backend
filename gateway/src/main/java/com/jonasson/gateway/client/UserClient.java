package com.jonasson.gateway.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

public class UserClient {
    private final String CUSTOMER_MICROSERVICE_NAME = "customer";
    private WebClient client;


    private DiscoveryClient discoveryClient;

    private void init(){
        ServiceInstance service = discoveryClient.getInstances(CUSTOMER_MICROSERVICE_NAME).stream().findFirst().orElse(null);
        client = WebClient.create(service.getUri().toString() + "/api/v1/customer/");
    }

    public Mono<Customer[]> getCustomers() {
        return client.get().retrieve().bodyToMono(Customer[].class);
    }

}
