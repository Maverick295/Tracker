package com.tracker.tracker.repositories;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    Optional<Payment> findByUuid(String uuid);

    List<Payment> findAllByCompany(Company company);

    void deleteByUuid(String uuid);
}
