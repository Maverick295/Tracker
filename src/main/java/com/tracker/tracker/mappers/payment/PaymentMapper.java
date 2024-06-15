package com.tracker.tracker.mappers.payment;

import com.tracker.tracker.dto.payment.PaymentDTO;
import com.tracker.tracker.entities.Payment;
import com.tracker.tracker.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper implements Mapper<PaymentDTO, Payment> {
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Payment mapTo(PaymentDTO source) {
        return modelMapper.map(source, Payment.class);
    }

    @Override
    public PaymentDTO mapFrom(Payment destination) {
        return modelMapper.map(destination, PaymentDTO.class);
    }
}
