package com.tracker.tracker.services.payment;

import com.tracker.tracker.dto.payment.PaymentDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Optional<Payment> findByUuid(String uuid);

    List<Payment> findAll(Company company);

    Payment getByUuid(String uuid);

    void save(Payment payment);

    Payment create(Company company, PaymentDTO dto);

    Payment update(String uuid, PaymentDTO dto);

    void deleteByUuid(String uuid);
}
