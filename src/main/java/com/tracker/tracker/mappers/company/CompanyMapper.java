package com.tracker.tracker.mappers.company;

import com.tracker.tracker.dto.company.CompanyDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements Mapper<CompanyDTO, Company> {
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Company mapTo(CompanyDTO source) {
        return modelMapper.map(source, Company.class);
    }

    @Override
    public CompanyDTO mapFrom(Company destination) {
        return modelMapper.map(destination, CompanyDTO.class);
    }
}
