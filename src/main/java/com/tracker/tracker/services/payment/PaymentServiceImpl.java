package com.tracker.tracker.services.payment;

import com.tracker.tracker.dto.payment.PaymentDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Payment;
import com.tracker.tracker.repositories.PaymentRepository;
import com.tracker.tracker.utils.ServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> findByUuid(String uuid) {
        return paymentRepository.findByUuid(uuid);
    }

    @Override
    public List<Payment> findAll(Company company) {
        return paymentRepository.findAllByCompany(company);
    }

    @Override
    public Payment getByUuid(String uuid) {
        return findByUuid(uuid)
            .orElseThrow(
                () -> new EntityNotFoundException("Payment with uuid " + uuid + " not found")
            );
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Payment create(
        Company company,
        PaymentDTO dto
    ) {
        return new Payment()
            .setUuid(ServiceUtil.generateUuid())
            .setCompany(company)
            .setCompanyName(dto.getCompanyName())
            .setInn(dto.getInn())
            .setKpp(dto.getKpp())
            .setOgrn(dto.getOgrn())
            .setPaymentAccount(dto.getPaymentAccount())
            .setBankName(dto.getBankName())
            .setBankBik(dto.getBankBik())
            .setCorrespondentAccount(dto.getCorrespondentAccount())
            .setBankInn(dto.getBankInn())
            .setBankKpp(dto.getBankKpp());
    }

    @Override
    public Payment update(
        String uuid,
        PaymentDTO dto
    ) {
        return getByUuid(uuid)
            .setCompanyName(dto.getCompanyName())
            .setInn(dto.getInn())
            .setKpp(dto.getKpp())
            .setOgrn(dto.getOgrn())
            .setPaymentAccount(dto.getPaymentAccount())
            .setBankName(dto.getBankName())
            .setBankBik(dto.getBankBik())
            .setCorrespondentAccount(dto.getCorrespondentAccount())
            .setBankInn(dto.getBankInn())
            .setBankKpp(dto.getBankKpp());
    }

    @Override
    public void deleteByUuid(String uuid) {
        paymentRepository.deleteByUuid(uuid);
    }
}
