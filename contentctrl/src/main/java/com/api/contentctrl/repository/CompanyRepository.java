package com.api.contentctrl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.contentctrl.modal.Company;

/**
 * Repository interface for managing Company entities in the database.
 * Extends JpaRepository to provide standard CRUD operations.
 * <p>
 * Created by: Vanderlei Soares de OLiveira
 * Creation Date: 2025-02-10
 * </p>
 * 
 * @author Vanderlei Soares de OLiveira
 * @version 1.0
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
