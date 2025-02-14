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
 * Created by: Vanderlei Soares de Oliveira
 * Creation Date: 2025-02-10
 * </p>
 * 
 * @author Vanderlei Soares de Oliveira
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
     * @return An Optional containing the company if found, otherwise empty.
     */
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    /**
     * Updates an existing company's details.
     *
     * @param id The ID of the company to update.
     * @param company The new details of the company.
     * @return An Optional containing the updated company if found, otherwise empty.
     */
    public Optional<Company> updateCompany(Long id, Company company) {
        return companyRepository.findById(id).map(existingCompany -> {
            company.setId(id);
            return companyRepository.save(company);
        });
    }

    /**
     * Deletes a company from the system by its ID.
     *
     * @param id The ID of the company to delete.
     * @return True if the company was deleted, false if not found.
     */
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
