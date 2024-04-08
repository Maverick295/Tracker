package com.tracker.tracker.repositories;


import com.tracker.tracker.entities.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
