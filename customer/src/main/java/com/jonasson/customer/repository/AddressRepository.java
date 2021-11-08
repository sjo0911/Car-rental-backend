package com.jonasson.customer.repository;

import com.jonasson.customer.entity.Address;
import com.jonasson.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
