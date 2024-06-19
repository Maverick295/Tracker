package com.tracker.tracker.controllers.payment;

import com.tracker.tracker.dto.payment.PaymentDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Payment;
import com.tracker.tracker.errors.AccessDeniedException;
import com.tracker.tracker.mappers.Mapper;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies/{uuid}/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final CompanyService companyService;
    private final Mapper<PaymentDTO, Payment> paymentMapper;

    @Autowired
    public PaymentController(
        PaymentService paymentService,
        CompanyService companyService,
        Mapper<PaymentDTO, Payment> paymentMapper
    ) {
        this.paymentService = paymentService;
        this.companyService = companyService;
        this.paymentMapper = paymentMapper;
    }

    @GetMapping
    public List<PaymentDTO> paymentsList(@PathVariable String uuid) {
        Company company = companyService.getByUuid(uuid);
        List<Payment> payments = paymentService.findAll(company);

        return payments.stream()
            .map(paymentMapper::mapFrom)
            .collect(Collectors.toList());
    }

    @GetMapping("/{payUuid}")
    public PaymentDTO moreAboutPayment(
        @PathVariable String uuid,
        @PathVariable String payUuid
    ) {
        Payment payment = paymentService.getByUuid(payUuid);

        return paymentMapper.mapFrom(payment);
    }

    @GetMapping("/{payUuid}/edit")
    public PaymentDTO editPayment(
        @PathVariable String uuid,
        @PathVariable String payUuid
    ) {
        Company company = companyService.getByUuid(uuid);
        Payment payment = paymentService.getByUuid(payUuid);

        if (!payment.getCompany().equals(company)) {
            throw new AccessDeniedException("You do not have permission to edit this payment");
        }

        return paymentMapper.mapFrom(payment);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createPayment(
        @PathVariable String uuid,
        @RequestBody PaymentDTO dto
    ) {
        Company company = companyService.getByUuid(uuid);
        Payment payment = paymentService.create(company, dto);
        paymentService.save(payment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{payUuid}")
    public ResponseEntity<HttpStatus> editPaymentPatch(
        @PathVariable String uuid,
        @PathVariable String payUuid,
        @RequestBody PaymentDTO dto
    ) {
        Payment payment = paymentService.update(payUuid, dto);
        paymentService.save(payment);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{payUuid}")
    public ResponseEntity<HttpStatus> deletePayment(
        @PathVariable String uuid,
        @PathVariable String payUuid
    ) {
        paymentService.deleteByUuid(payUuid);

        return ResponseEntity.noContent().build();
    }
}
