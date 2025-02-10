package com.api.contentctrl.modal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Schema(description = "Entity representing a company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique ID of the company", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Company name cannot be blank")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    @Schema(description = "Name of the company", example = "Tech Corp", required = true)
    private String name;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    @Schema(description = "Address of the company", example = "1234 Elm Street, New York, NY", required = true)
    private String address;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\+?[0-9. ()-]{7,20}", message = "Invalid phone number format")
    @Schema(description = "Phone number of the company", example = "+1 555-1234", required = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Prevents infinite recursion when serializing
    @Schema(description = "List of branches associated with the company")
    private List<Branch> branches;

    // Default constructor
    public Company() {}

    // Constructor with parameters
    public Company(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
