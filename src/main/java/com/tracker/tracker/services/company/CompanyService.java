package com.tracker.tracker.services.company;

import com.tracker.tracker.dto.company.CompanyDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.User;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Optional<Company> findByUuid(String uuid);

    List<Company> findAll(User user);

    Company getByUuid(String uuid);

    void deleteByUuid(String uuid);

    void save(Company company);

    Company create(CompanyDTO form);

    Company edit(CompanyDTO form, String uuid);
}
