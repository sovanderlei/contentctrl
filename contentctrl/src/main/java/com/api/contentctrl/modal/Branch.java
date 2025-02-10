package com.api.contentctrl.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "Entity representing a branch of a company")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique ID of the branch", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Branch name cannot be blank")
    @Size(min = 2, max = 100, message = "Branch name must be between 2 and 100 characters")
    @Schema(description = "Name of the branch", example = "Downtown Office", required = true)
    private String name;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    @Schema(description = "Address of the branch", example = "5678 Oak Street, Los Angeles, CA", required = true)
    private String address;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonBackReference // Prevents infinite recursion when serializing
    @Schema(description = "Company to which this branch belongs")
    private Company company;

    // Default constructor
    public Branch() {}

    // Constructor with parameters
    public Branch(String name, String address, Company company) {
        this.name = name;
        this.address = address;
        this.company = company;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
