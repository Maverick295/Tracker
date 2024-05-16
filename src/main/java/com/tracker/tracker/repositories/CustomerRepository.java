package com.tracker.tracker.repositories;

import com.tracker.tracker.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByUsername(String username);
    Customer findByEmail(String email);
}
