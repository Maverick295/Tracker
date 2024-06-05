package com.tracker.tracker.controllers.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.entities.Customer;
import com.tracker.tracker.forms.company.CompanyForm;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.customer.CustomerService;
import com.tracker.tracker.services.models.ModelService;
import com.tracker.tracker.utils.RedirectUtil;
import com.tracker.tracker.validators.company.CompanyFormValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/company")
public class CompanyRestController {
    private final CompanyService companyService;
    private final ModelService modelService;
    private final CustomerService customerService;
    private final CompanyFormValidator companyFormValidator;

    @Autowired
    public CompanyRestController(
            CompanyService companyService,
            ModelService modelService,
            CustomerService customerService,
            CompanyFormValidator companyFormValidator
    ) {
        this.companyService = companyService;
        this.modelService = modelService;
        this.customerService = customerService;
        this.companyFormValidator = companyFormValidator;
    }

    @InitBinder("companyForm")
    public void setCompanyForm(WebDataBinder binder) {
        binder.addValidators(companyFormValidator);
    }

    @PostMapping("/create")
    public ModelAndView createCompanyAction(
            @ModelAttribute @Valid CompanyForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return new ModelAndView("/company/company-create")
                    .addObject("companyCreateForm", new CompanyForm());
        }
        Company company = companyService.createCompany(form);
        companyService.save(company);

        return RedirectUtil.redirect("/company/view");
    }

    @DeleteMapping("/delete/{uuid}")
    public ModelAndView deleteCompany(
            @PathVariable String uuid
    ) {
        Company company = companyService.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company.getCustomer().equals(currentCustomer)) {
            companyService.deleteCompanyByUuid(uuid);
            return RedirectUtil.redirect("/company/view");
        }

        return RedirectUtil.redirect("/view");
    }

    @PostMapping("/edit/{uuid}")
    public ModelAndView updateCompanyAction(
            @PathVariable String uuid,
            @ModelAttribute @Valid CompanyForm form,
            BindingResult result
    ) {
        Company company = companyService.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        Customer currentCustomer = customerService.getAuthenticatedCustomer();

        if (company.getCustomer().equals(currentCustomer)) {
            if (result.hasErrors()) {
                return new ModelAndView("/company/company-edit")
                        .addObject("company", modelService.getCompanyModel(company))
                        .addObject("companyForm", new CompanyForm());
            }
            company = companyService.editCompany(form, uuid);
            companyService.save(company);

            return RedirectUtil.redirect("/company/{uuid}");
        }

        return RedirectUtil.redirect("/view");
    }
}
