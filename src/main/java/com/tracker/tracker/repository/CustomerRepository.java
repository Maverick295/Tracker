package com.tracker.tracker.repository;

import com.tracker.tracker.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByUsername(String username);
    Customer findByEmail(String email);
}
