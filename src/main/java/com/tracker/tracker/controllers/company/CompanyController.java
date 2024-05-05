package com.tracker.tracker.controllers.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.company.CompanyCreateForm;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelCompanyService;
import com.tracker.tracker.utils.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelCompanyService modelCompanyService;
    private final CustomerService customerService;

    @Autowired
    public CompanyController(CompanyService companyService, ModelCompanyService modelCompanyService, CustomerService customerService) {
        this.companyService = companyService;
        this.modelCompanyService = modelCompanyService;
        this.customerService = customerService;
    }

    @GetMapping("/create")
    public ModelAndView createCompany() {
        return new ModelAndView("companytempl/company-create")
                .addObject("companyCreateForm", new CompanyCreateForm());
    }

    @PostMapping("/create")
    public ModelAndView createCompanyAction(@ModelAttribute @Valid CompanyCreateForm form) {
        Company company = companyService.createCompany(form);
        companyService.save(company);
        return RedirectUtil.redirect("/");
    }

    @GetMapping("/info/{uuid}")
    public ModelAndView companyInfo(@PathVariable String uuid) {
        Optional<Company> optionalCompany = companyService.findByUuid(uuid);
        Company company = optionalCompany.orElse(null);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company != null && company.getCustomer().equals(currentCustomer)) {
            // Если да, возвращаем информацию о компании
            return new ModelAndView("companytempl/company-info").addObject("company", modelCompanyService.getCompanyModel(company));
        } else {
            // Если нет, возвращаем ошибку доступа или что-то еще
            return new ModelAndView("error").addObject("message", "Access denied");
        }
    }

    @DeleteMapping("/{uuid}")
    public ModelAndView deleteCompany(@PathVariable String uuid) {
        Optional<Company> optionalCompany = companyService.findByUuid(uuid);
        Company company = optionalCompany.orElse(null);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company != null && company.getCustomer().equals(currentCustomer)) {
            companyService.deleteCompanyByUuid(uuid);
            return RedirectUtil.redirect("/company/view");
        } else {
            // Если нет, возвращаем ошибку доступа или что-то еще
            return new ModelAndView("error").addObject("message", "Access denied");
        }
    }

    @GetMapping("/view")
    public ModelAndView getTeams(@PageableDefault Pageable pageable) {
        Page<CompanyModel> companyPage = companyService.getCompanies(pageable);
        return new ModelAndView("companytempl/companies").addObject("companyPage", companyPage);
    }
}
