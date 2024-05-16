package com.tracker.tracker.controllers.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.company.CompanyForm;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelCompanyService;
import com.tracker.tracker.utils.RedirectUtil;
import com.tracker.tracker.validators.company.CompanyFormValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelCompanyService modelCompanyService;
    private final CustomerService customerService;
    private final CompanyFormValidator companyFormValidator;

    @Autowired
    public CompanyController(CompanyService companyService, ModelCompanyService modelCompanyService, CustomerService customerService, CompanyFormValidator companyFormValidator) {
        this.companyService = companyService;
        this.modelCompanyService = modelCompanyService;
        this.customerService = customerService;
        this.companyFormValidator = companyFormValidator;
    }

    @InitBinder("companyForm")
    public void setCompanyForm(WebDataBinder binder) {
        binder.addValidators(companyFormValidator);
    }

    @GetMapping("/create")
    public ModelAndView createCompany() {
        return new ModelAndView("company/company-create")
                .addObject("companyCreateForm", new CompanyForm());
    }

    @PostMapping("/create")
    public ModelAndView createCompanyAction(@ModelAttribute @Valid CompanyForm form) {
        Company company = companyService.createCompany(form);
        companyService.save(company);
        return RedirectUtil.redirect("/");
    }

    @GetMapping("/{uuid}")
    public ModelAndView getCompanyInfo(@PathVariable String uuid) {
        Company company = companyService.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company.getCustomer().equals(currentCustomer)) {
            // Если да, возвращаем информацию о компании
            return new ModelAndView("company/company-info")
                    .addObject("company", modelCompanyService.getCompanyModel(company));
        } else {
            // Если нет, возвращаем ошибку доступа или что-то еще
            return new ModelAndView("error")
                    .addObject("message", "Access denied");
        }
    }

    @DeleteMapping("/delete/{uuid}")
    public ModelAndView deleteCompany(@PathVariable String uuid) {
        Company company = companyService.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company.getCustomer().equals(currentCustomer)) {
            companyService.deleteCompanyByUuid(uuid);
            return RedirectUtil.redirect("/company/view");
        } else {
            // Если нет, возвращаем ошибку доступа или что-то еще
            return new ModelAndView("error")
                    .addObject("message", "Access denied");
        }
    }

    @GetMapping("/view")
    public ModelAndView getTeams(@PageableDefault Pageable pageable) {
        Customer customer = customerService.getAuthenticatedCustomer();
        Page<CompanyModel> companyPage = companyService.getCompanies(pageable, customer);

        return new ModelAndView("company/companies")
                .addObject("companyPage", companyPage);
    }

    @GetMapping("/edit/{uuid}")
    public ModelAndView updateCompanyInfo(@PathVariable String uuid) {
        Company company = companyService.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company.getCustomer().equals(currentCustomer)) {
            return new ModelAndView("company/company-edit")
                    .addObject("company", modelCompanyService.getCompanyModel(company))
                    .addObject("companyForm", new CompanyForm());
        } else {
            // Если нет, возвращаем ошибку доступа или что-то еще
            return new ModelAndView("error")
                    .addObject("message", "Access denied");
        }

    }

    @PostMapping("/edit/{uuid}")
    public ModelAndView updateCompanyAction(@ModelAttribute @Valid CompanyForm form, @PathVariable String uuid) {
        Company company = companyService.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company.getCustomer().equals(currentCustomer)) {
            String redirectUrl = String.join("", "/company/", uuid);

            company = companyService.editCompany(form, uuid);
            companyService.save(company);

            return RedirectUtil.redirect(redirectUrl);
        } else {
            // Если нет, возвращаем ошибку доступа или что-то еще
            return new ModelAndView("error")
                    .addObject("message", "Access denied");
        }
    }
}
