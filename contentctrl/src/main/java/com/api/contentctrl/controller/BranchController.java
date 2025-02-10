package com.api.contentctrl.controller;

import com.api.contentctrl.modal.Branch;
import com.api.contentctrl.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contentctrl/branches")
@Tag(name = "Branch Controller", description = "Operations related to branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Operation(summary = "Get all branches", description = "Returns a list of all branches in the system.")
    @ApiResponse(responseCode = "200", description = "Branches successfully retrieved")
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @Operation(summary = "Create a new branch", description = "Creates a new branch in the system.")
    @ApiResponse(responseCode = "201", description = "Branch successfully created")
    @PostMapping
    public Branch createBranch(
        @Parameter(description = "Information of the new branch to be created") @RequestBody Branch branch) {
        return branchService.createBranch(branch);
    }

    @Operation(summary = "Get branch by ID", description = "Returns details of a branch based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Branch found successfully")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    @GetMapping("/{id}")
    public Branch getBranchById(
        @Parameter(description = "ID of the branch to be retrieved") @PathVariable Long id) {
        return branchService.getBranchById(id);
    }

    @Operation(summary = "Update a branch", description = "Updates the details of an existing branch.")
    @ApiResponse(responseCode = "200", description = "Branch successfully updated")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    @PutMapping("/{id}")
    public Branch updateBranch(
        @Parameter(description = "ID of the branch to be updated") @PathVariable Long id,
        @Parameter(description = "Branch data to be updated") @RequestBody Branch branch) {
        return branchService.updateBranch(id, branch);
    }

    @Operation(summary = "Delete a branch", description = "Deletes a branch based on the provided ID.")
    @ApiResponse(responseCode = "200", description = "Branch successfully deleted")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    @DeleteMapping("/{id}")
    public void deleteBranch(@Parameter(description = "ID of the branch to be deleted") @PathVariable Long id) {
        branchService.deleteBranch(id);
    }
}
