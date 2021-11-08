package com.jonasson.customer.helper;

import com.jonasson.customer.dto.AddressDTO;
import com.jonasson.customer.dto.CustomerDTO;
import com.jonasson.customer.entity.Address;
import com.jonasson.customer.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static CustomerDTO customerToDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress(addressToDto(customer.getAddress()));
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setId(customer.getId());
        customerDTO.setSocialSecurityNumber(customer.getSocialSecurityNumber());
        return customerDTO;
    }

    private static AddressDTO addressToDto(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setStreetNumber(address.getStreetNumber());
        return addressDTO;
    }

    public static List<CustomerDTO> customerListToDTOS(List<Customer> customers){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customers.forEach(customer -> customerDTOS.add(customerToDto(customer)));
        return customerDTOS;
    }
}
