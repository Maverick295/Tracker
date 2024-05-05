package com.tracker.tracker.repositories;


import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    Page<Company> findAllByCustomer(Pageable pageable, Customer customer);
    Optional<Company> findCompanyByUuid(String uuid);
    void deleteByUuid(String uuid);
}
