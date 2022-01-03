package com.jonasson.customer.controller;

import com.jonasson.customer.dto.CustomerDTO;
import com.jonasson.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/singlecustomer")
public class CustomerControllerSingleCustomer {

    @Autowired
    CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/withusername/{username}")
    private CustomerDTO getCustomerByUsername(@PathVariable("username") String username){
        logger.info("Någon hämta kund med användarnamn " + username);
        return customerService.getCustomerByUsername(username);}

    @GetMapping("/withid/{id}")
    private CustomerDTO getCustomer(@PathVariable("id") Long id){
        logger.info("Någon hämta kund med id " + id);
        return customerService.findCustomerById(id);}
}
