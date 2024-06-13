package com.tracker.tracker.repositories;


import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findAllByUser(User user);

    Optional<Company> findCompanyByUuid(String uuid);

    void deleteByUuid(String uuid);
}
