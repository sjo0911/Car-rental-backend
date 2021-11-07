package com.jonasson.customer.service;

import com.jonasson.customer.entity.Customer;
import com.jonasson.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findCustomers(){return customerRepository.findAll();}

    public Customer findCustomerById(Long id){return customerRepository.getById(id);}

    public Customer postUpdateCustomer(Customer customer){return customerRepository.save(customer);}

    public void deleteCustomer(Long id){customerRepository.deleteById(id);}
}
