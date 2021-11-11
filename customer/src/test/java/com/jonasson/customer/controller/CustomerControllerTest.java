package com.jonasson.customer.controller;

import com.jonasson.customer.dto.CustomerDTO;
import com.jonasson.customer.entity.Address;
import com.jonasson.customer.entity.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    CustomerController customerController;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        assertNotNull(customerController);
    }

    @Test
    @DisplayName("CUSTOMER REST can get all customers")
    void getAll(){
        CustomerDTO[] customerDTOS = testRestTemplate.getForObject("/api/v1/customer/", CustomerDTO[].class);
        assertEquals(1, customerDTOS.length);
    }

    @Test
    @DisplayName("CUSTOMER REST can get customer by id 1 that should contain a customer id equal to 1")
    void getBYId(){
        CustomerDTO customerDTO = testRestTemplate.getForObject("/api/v1/customer/1/", CustomerDTO.class);
        assertEquals(1, customerDTO.getId());
    }

    @BeforeAll
    @Test
    @DisplayName("CUSTOMER REST can post customer")
    void postCustomer(){
        //Blev krångligt att lägga in data genom fil så lägger in data första som görs i testen som ett ful hack
        Customer customerDTO = new Customer();
        customerDTO.setSocialSecurityNumber("1987-09-11-0000");
        customerDTO.setLastName("Jonasson");
        customerDTO.setFirstName("Simon");
        customerDTO.setEmail("example@mail.com");
        Address addressDTO = new Address();
        addressDTO.setStreet("gatan");
        addressDTO.setStreetNumber(1);
        addressDTO.setCity("Skellefteå");
        customerDTO.setAddress(addressDTO);
        ResponseEntity<Customer> postedCustomer = testRestTemplate.postForEntity("/api/v1/customer/", customerDTO, Customer.class);
        assertEquals("200 OK", postedCustomer.getStatusCode().toString());
    }
}