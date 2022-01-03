package com.jonasson.orderapi.service;

import com.jonasson.orderapi.client.BookingClient;
import com.jonasson.orderapi.client.CarClient;
import com.jonasson.orderapi.client.CustomerClient;
import com.jonasson.orderapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class CustomerService {
    @Autowired
    BookingClient bookingClient;
    @Autowired
    CustomerClient customerClient;

    public List<Customer> getCustomersWithNumberOfBookings(){
        List<Customer> customers = Arrays.asList(customerClient.getCustomer().block());
        customers.forEach(customer -> customer.setNumOfBookings(bookingClient.getBookingsByCustomerId(customer.getId()).block().length));
        return customers;
    }
}
