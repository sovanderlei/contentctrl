package com.api.contentctrl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.contentctrl.modal.Branch;

/**
 * Repository interface for {@link Branch} entity.
 * <p>
 * This interface extends the {@link JpaRepository} interface, which provides 
 * various methods for performing CRUD operations on the {@link Branch} entity.
 * Spring Data JPA will automatically implement this interface.
 * </p>
 * 
 * <p>
 * Created by: Vanderlei Soares de OLiveira
 * Creation Date: 2025-02-10
 * </p>
 * 
 * @author Vanderlei Soares de OLiveira
 * @version 1.0
 */
public interface BranchRepository extends JpaRepository<Branch, Long> {
    
    /**
     * Custom query to find branches by name (if needed in the future).
     * Spring Data JPA will automatically implement methods following the naming conventions.
     *
     * @param name the name of the branch to search for
     * @return a list of branches matching the provided name
     */
    // List<Branch> findByName(String name);
    
}
