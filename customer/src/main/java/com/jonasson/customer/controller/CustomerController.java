package com.jonasson.customer.controller;

import com.jonasson.customer.dto.CustomerDTO;
import com.jonasson.customer.entity.Customer;
import com.jonasson.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/")
    private List<CustomerDTO> getCustomers(){
        logger.info("Någon hämtade alla kunder");
        return customerService.findCustomers();}

    @GetMapping("/{id}")
    private CustomerDTO getCustomer(@PathVariable("id") Long id){
        logger.info("Någon hämta kund med id " + id);
        return customerService.findCustomerById(id);}



    @PostMapping("/")
    private Customer postCustomer(@RequestBody Customer customer){
        logger.info("Någon skapade ny kund");
        return customerService.postUpdateCustomer(customer);}

    @DeleteMapping("/{id}")
    private void deleteCustomer(@PathVariable("id") Long id){
        logger.info("Någon tog bort en kund");
        customerService.deleteCustomer(id);}
}
