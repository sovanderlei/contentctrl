package com.api.contentctrl.controller;

import com.api.contentctrl.modal.Company;
import com.api.contentctrl.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contentctrl/companies")
@Tag(name = "Company Controller", description = "Operations related to companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Operation(summary = "Get all companies", description = "Returns a list of all companies in the system.")
    @ApiResponse(responseCode = "200", description = "Companies successfully retrieved")
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);  // Status 200 OK
    }

    @Operation(summary = "Create a new company", description = "Creates a new company in the system.")
    @ApiResponse(responseCode = "201", description = "Company successfully created")
    @PostMapping
    public ResponseEntity<Company> createCompany(
        @Parameter(description = "Information of the new company to be created") @RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.status(201).body(createdCompany);  // Status 201 Created
    }

    @Operation(summary = "Get company by ID", description = "Returns details of a company based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Company found successfully")
    @ApiResponse(responseCode = "404", description = "Company not found")
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(
        @Parameter(description = "ID of the company to be retrieved") @PathVariable Long id) {
        Optional<Company> companyOptional = companyService.getCompanyById(id);
        if (companyOptional.isPresent()) {
            return ResponseEntity.ok(companyOptional.get());  // Status 200 OK
        } else {
            return ResponseEntity.status(404).build();  // Status 404 Not Found
        }
    }

    @Operation(summary = "Update a company", description = "Updates the details of an existing company.")
    @ApiResponse(responseCode = "200", description = "Company successfully updated")
    @ApiResponse(responseCode = "404", description = "Company not found")
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(
        @Parameter(description = "ID of the company to be updated") @PathVariable Long id,
        @Parameter(description = "Company data to be updated") @RequestBody Company company) {
        Optional<Company> updatedCompanyOptional = companyService.updateCompany(id, company);
        if (updatedCompanyOptional.isPresent()) {
            return ResponseEntity.ok(updatedCompanyOptional.get());  // Status 200 OK
        } else {
            return ResponseEntity.status(404).build();  // Status 404 Not Found
        }
    }

    @Operation(summary = "Delete a company", description = "Deletes a company based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Company successfully deleted")
    @ApiResponse(responseCode = "404", description = "Company not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@Parameter(description = "ID of the company to be deleted") @PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompany(id);
        if (isDeleted) {
            return ResponseEntity.status(200).build();  // Status 200 OK
        } else {
            return ResponseEntity.status(404).build();  // Status 404 Not Found
        }
    }
}
