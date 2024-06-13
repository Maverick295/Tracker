package com.tracker.tracker.controllers.company;

import com.tracker.tracker.dto.company.CompanyDTO;
import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.User;
import com.tracker.tracker.mappers.Mapper;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.customer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyRestController {
    private final CompanyService companyService;
    private final UserService userService;
    private final Mapper<CompanyDTO, Company> companyMapper;

    @Autowired
    public CompanyRestController(
        CompanyService companyService,
        UserService userService,
        Mapper<CompanyDTO, Company> companyMapper
    ) {
        this.companyService = companyService;
        this.userService = userService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyDTO> companiesList() {
        User user = userService.getAuthenticatedUser();
        List<Company> companies = companyService.findAll(user);

        return companies.stream()
            .map(companyMapper::mapFrom)
            .collect(Collectors.toList());
    }


    @GetMapping("/{uuid}")
    public CompanyDTO moreAboutCompany(@PathVariable String uuid) {
        Company company = companyService.getByUuid(uuid);

        return companyMapper.mapFrom(company);
    }

    @GetMapping("/{uuid}/edit")
    public CompanyDTO editCompany(@PathVariable String uuid) {
        if (!companyService.getByUuid(uuid).getUser().equals(userService.getAuthenticatedUser())) {
            throw new AccessDeniedException("You do not have permission to edit this company");
        }
        Company company = companyService.getByUuid(uuid);

        return companyMapper.mapFrom(company);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createCompany(@RequestBody CompanyDTO dto) {
        Company company = companyService.create(dto);
        companyService.save(company);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{uuid}/edit")
    public ResponseEntity<HttpStatus> editCompanyPost(
        @PathVariable String uuid,
        @RequestBody CompanyDTO dto
    ) {
        Company company = companyService.edit(dto, uuid);
        companyService.save(company);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}/delete")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable String uuid) {
        companyService.deleteByUuid(uuid);

        return ResponseEntity.noContent().build();
    }
}
