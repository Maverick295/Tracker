package com.tracker.tracker.services.company;

import com.tracker.tracker.dto.company.CompanyDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.repositories.CompanyRepository;
import com.tracker.tracker.services.user.UserService;
import com.tracker.tracker.utils.ServiceUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;

    @Autowired
    public CompanyServiceImpl(
        CompanyRepository companyRepository,
        UserService userService
    ) {
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Company> findByUuid(String uuid) {
        return companyRepository.findCompanyByUuid(uuid);
    }

    @Override
    public List<Company> findAll(User user) {
        return companyRepository.findAllByUser(user);
    }

    @Override
    public Company getByUuid(String uuid) {
        return findByUuid(uuid)
            .orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));
    }

    @Override
    public void deleteByUuid(String uuid) {
        companyRepository.deleteByUuid(uuid);
    }


    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company create(CompanyDTO dto) {
        return new Company()
            .setCompanyName(dto.getCompanyName())
            .setLegalEntity(dto.getLegalEntity())
            .setInn(dto.getInn())
            .setBankAccount(dto.getBankAccount())
            .setBankBik(dto.getBankBik())
            .setLegalAddress(dto.getLegalAddress())
            .setActualAddress(dto.getActualAddress())
            .setEmail(dto.getEmail())
            .setPhoneNumber(dto.getPhoneNumber())
            .setOgrn(dto.getOgrn())
            .setOkpo(dto.getOkpo())
            .setDateOfCreate(LocalDate.now())
            .setUser(userService.getAuthenticatedUser())
            .setUuid(ServiceUtil.generateUuid())
            .setKpp(dto.getKpp())
            .setBankInn(dto.getBankInn())
            .setBankKpp(dto.getBankKpp())
            .setBankName(dto.getBankName())
            .setCorrespondentAccount(dto.getCorrespondentAccount())
            .setKpp(dto.getKpp())
            .setDirectorName(dto.getDirectorName())
            .setDirectorSurname(dto.getDirectorSurname())
            .setDirectorPatronymic(dto.getDirectorPatronymic())
            .setOgrnip(dto.getOgrnip());
    }

    @Override
    public Company edit(
        CompanyDTO dto,
        String uuid
    ) {
        return getByUuid(uuid)
            .setCompanyName(dto.getCompanyName())
            .setLegalEntity(dto.getLegalEntity())
            .setInn(dto.getInn())
            .setBankAccount(dto.getBankAccount())
            .setBankBik(dto.getBankBik())
            .setLegalAddress(dto.getLegalAddress())
            .setActualAddress(dto.getActualAddress())
            .setEmail(dto.getEmail())
            .setPhoneNumber(dto.getPhoneNumber())
            .setOgrn(dto.getOgrn())
            .setOkpo(dto.getOkpo())
            .setKpp(dto.getKpp())
            .setBankInn(dto.getBankInn())
            .setBankKpp(dto.getBankKpp())
            .setBankName(dto.getBankName())
            .setCorrespondentAccount(dto.getCorrespondentAccount())
            .setKpp(dto.getKpp())
            .setDirectorName(dto.getDirectorName())
            .setDirectorSurname(dto.getDirectorSurname())
            .setDirectorPatronymic(dto.getDirectorPatronymic())
            .setOgrnip(dto.getOgrnip());
    }
}
