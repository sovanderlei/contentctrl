package com.api.contentctrl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.contentctrl.modal.Branch;
import com.api.contentctrl.repository.BranchRepository;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing branch-related operations,
 * including retrieval, creation, updating, and deletion of branch records.
 * <p>
 * Created by: Vanderlei Soares de OLiveira
 * Creation Date: 2025-02-10
 * </p>
 * 
 * @author Vanderlei Soares de OLiveira
 * @version 1.0
 */
@Service
public class BranchService {

    private final BranchRepository branchRepository;

    /**
     * Constructor for BranchService, initializing the repository dependency.
     *
     * @param branchRepository The repository for branch-related database operations.
     */
    @Autowired
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    /**
     * Retrieves a list of all branches.
     *
     * @return A list containing all branches.
     */
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    /**
     * Creates a new branch and stores it in the database.
     *
     * @param branch The branch object containing the branch details.
     * @return The created branch.
     */
    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    /**
     * Retrieves a branch by its ID.
     *
     * @param id The ID of the branch to fetch.
     * @return The branch object if found, otherwise null.
     */
    public Branch getBranchById(Long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        return branch.orElse(null);
    }

    /**
     * Updates an existing branch's details.
     *
     * @param id The ID of the branch to update.
     * @param branch The new details of the branch.
     * @return The updated branch, or null if the branch is not found.
     */
    public Branch updateBranch(Long id, Branch branch) {
        if (branchRepository.existsById(id)) {
            branch.setId(id);
            return branchRepository.save(branch);
        }
        return null;
    }

    /**
     * Deletes a branch from the system by its ID.
     *
     * @param id The ID of the branch to delete.
     */
    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }
}
