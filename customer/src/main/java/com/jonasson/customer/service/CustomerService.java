package com.jonasson.customer.service;

import com.jonasson.customer.dto.CustomerDTO;
import com.jonasson.customer.entity.Address;
import com.jonasson.customer.entity.Customer;
import com.jonasson.customer.helper.Mapper;
import com.jonasson.customer.repository.AddressRepository;
import com.jonasson.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<CustomerDTO> findCustomers(){return Mapper.customerListToDTOS(customerRepository.findAll());}

    public CustomerDTO findCustomerById(Long id){return Mapper.customerToDto(customerRepository.getById(id));}

    public Customer postUpdateCustomer(Customer customer){
        Address address = addressRepository.save(customer.getAddress());
        customer.getAddress().setId(address.getId());
        return customerRepository.save(customer);}

    public void deleteCustomer(Long id){customerRepository.deleteById(id);}

    public CustomerDTO getCustomerByUsername(String username) {
        return Mapper.customerToDto(customerRepository.getCustomersByUsername(username));
    }
}
