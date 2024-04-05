package com.tracker.tracker.repositories;

import com.tracker.tracker.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByUsername(String username);
    Customer findByEmail(String email);
}
