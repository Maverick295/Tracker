package com.tracker.tracker.controllers.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.company.CompanyForm;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import com.tracker.tracker.utils.RedirectUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final ModelService modelService;

    @Autowired
    public CompanyController(
            CompanyService companyService,
            CustomerService customerService,
            ModelService modelService
    ) {
        this.companyService = companyService;
        this.customerService = customerService;
        this.modelService = modelService;
    }

    @GetMapping("/view")
    public ModelAndView getTeams(
            @PageableDefault Pageable pageable
    ) {
        Customer customer = customerService.getAuthenticatedCustomer();
        Page<CompanyModel> companyPage = companyService.getCompanies(pageable, customer);

        return new ModelAndView("/company/companies")
                .addObject("companyPage", companyPage);
    }

    @GetMapping("/create")
    public ModelAndView createCompany() {
        return new ModelAndView("/company/company-create")
                .addObject("companyCreateForm", new CompanyForm());
    }

    @GetMapping("/edit/{uuid}")
    public ModelAndView updateCompanyInfo(
            @PathVariable String uuid
    ) {
        Company company = companyService.getCompanyByUuid(uuid);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company.getCustomer().equals(currentCustomer)) {
            return new ModelAndView("/company/company-edit")
                    .addObject("company", modelService.getCompanyModel(company))
                    .addObject("companyForm", new CompanyForm());
        }

        return RedirectUtil.redirect("/view");
    }

    @GetMapping("/{uuid}")
    public ModelAndView getCompanyInfo(
            @PathVariable String uuid
    ) {
        Company company = companyService.getCompanyByUuid(uuid);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company.getCustomer().equals(currentCustomer)) {
            return new ModelAndView("/company/company-info")
                    .addObject("company", modelService.getCompanyModel(company));
        }

        return RedirectUtil.redirect("/view");
    }
}
