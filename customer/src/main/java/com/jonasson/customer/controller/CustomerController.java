package com.jonasson.customer.controller;

import com.jonasson.customer.entity.Customer;
import com.jonasson.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    private List<Customer> getCustomers(){return customerService.findCustomers();}

    @GetMapping("/{id}")
    private  Customer getCustomer(@PathVariable("id") Long id){return customerService.findCustomerById(id);}

    @PostMapping("/")
    private Customer postCustomer(@RequestBody Customer customer){return customerService.postUpdateCustomer(customer);}

    @DeleteMapping("/{id}")
    private void deleteCustomer(@PathVariable("id") Long id){customerService.deleteCustomer(id);}
}
