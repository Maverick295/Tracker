package com.tracker.tracker.controllers.company;

import com.tracker.tracker.entities.Company;
import com.tracker.tracker.forms.company.CompanyCreateForm;
import com.tracker.tracker.models.company.CompanyModel;
import com.tracker.tracker.services.company.CompanyService;
import com.tracker.tracker.services.models.ModelCompanyService;
import com.tracker.tracker.utils.RedirectUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CompanyController(CompanyService companyService, ModelCompanyService modelCompanyService) {
        this.companyService = companyService;
        this.modelCompanyService = modelCompanyService;
    }

    @GetMapping("/create")
    public ModelAndView createCompany(){
        return new ModelAndView("companytempl/company-create")
                .addObject("companyCreateForm", new CompanyCreateForm());
    }

    @PostMapping("/create")
    public ModelAndView createCompanyAction(@ModelAttribute @Valid CompanyCreateForm form){
        Company company = companyService.createCompany(form);
        companyService.save(company);
        return RedirectUtil.redirect("/");
    }

    @GetMapping("/info/{id}")
    public ModelAndView companyInfo(@PathVariable Long id){
        Optional<Company> optionalCompany = companyService.findById(id);
        Company company = optionalCompany.orElse(null);
        return new ModelAndView("companytempl/company-info").addObject("company", modelCompanyService.getCompanyModel(company));
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
        return RedirectUtil.redirect("/company/view");
    }
    @GetMapping("/view")
    public ModelAndView getTeams(@PageableDefault Pageable pageable) {
        Page<CompanyModel> companyPage = companyService.getCompanies(pageable);
        return new ModelAndView("companytempl/companies").addObject("companyPage", companyPage);
    }
}
