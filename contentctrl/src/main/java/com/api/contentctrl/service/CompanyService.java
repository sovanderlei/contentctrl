package com.api.contentctrl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.contentctrl.modal.Company;
import com.api.contentctrl.repository.CompanyRepository;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing company-related operations,
 * including retrieval, creation, updating, and deletion of company records.
 * <p>
 * Created by: Vanderlei Soares de OLiveira
 * Creation Date: 2025-02-10
 * </p>
 * 
 * @author Vanderlei Soares de OLiveira
 * @version 1.0
 */
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    /**
     * Constructor for CompanyService, initializing the repository dependency.
     *
     * @param companyRepository The repository for company-related database operations.
     */
    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Retrieves a list of all companies.
     *
     * @return A list containing all companies.
     */
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    /**
     * Creates a new company and stores it in the database.
     *
     * @param company The company object containing the company details.
     * @return The created company.
     */
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    /**
     * Retrieves a company by its ID.
     *
     * @param id The ID of the company to fetch.
     * @return The company object if found, otherwise null.
     */
    public Company getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElse(null);
    }

    /**
     * Updates an existing company's details.
     *
     * @param id The ID of the company to update.
     * @param company The new details of the company.
     * @return The updated company, or null if the company is not found.
     */
    public Company updateCompany(Long id, Company company) {
        if (companyRepository.existsById(id)) {
            company.setId(id);
            return companyRepository.save(company);
        }
        return null;
    }

    /**
     * Deletes a company from the system by its ID.
     *
     * @param id The ID of the company to delete.
     */
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}