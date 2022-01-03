package com.jonasson.customer.repository;

import com.jonasson.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("FROM Customer c where c.userName = ?1")
    Customer getCustomersByUsername(String username);
}
