package com.api.contentctrl.controller;

import com.api.contentctrl.modal.Company;
import com.api.contentctrl.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contentctrl/companies")
@Tag(name = "Company Controller", description = "Operations related to companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Operation(summary = "Get all companies", description = "Returns a list of all companies in the system.")
    @ApiResponse(responseCode = "200", description = "Companies successfully retrieved")
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @Operation(summary = "Create a new company", description = "Creates a new company in the system.")
    @ApiResponse(responseCode = "201", description = "Company successfully created")
    @PostMapping
    public Company createCompany(
        @Parameter(description = "Information of the new company to be created") @RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @Operation(summary = "Get company by ID", description = "Returns details of a company based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Company found successfully")
    @ApiResponse(responseCode = "404", description = "Company not found")
    @GetMapping("/{id}")
    public Company getCompanyById(
        @Parameter(description = "ID of the company to be retrieved") @PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @Operation(summary = "Update a company", description = "Updates the details of an existing company.")
    @ApiResponse(responseCode = "200", description = "Company successfully updated")
    @ApiResponse(responseCode = "404", description = "Company not found")
    @PutMapping("/{id}")
    public Company updateCompany(
        @Parameter(description = "ID of the company to be updated") @PathVariable Long id,
        @Parameter(description = "Company data to be updated") @RequestBody Company company) {
        return companyService.updateCompany(id, company);
    }

    @Operation(summary = "Delete a company", description = "Deletes a company based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Company successfully deleted")
    @ApiResponse(responseCode = "404", description = "Company not found")
    @DeleteMapping("/{id}")
    public void deleteCompany(@Parameter(description = "ID of the company to be deleted") @PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}
