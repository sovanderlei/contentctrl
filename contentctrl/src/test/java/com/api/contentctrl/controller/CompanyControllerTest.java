package com.api.contentctrl.controller;
 
import com.api.contentctrl.modal.Company;
import com.api.contentctrl.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    void testGetAllCompanies() throws Exception {
        Company company1 = new Company();
        company1.setName("Company 1");

        Company company2 = new Company();
        company2.setName("Company 2");

        List<Company> companyList = Arrays.asList(company1, company2);

        when(companyService.getAllCompanies()).thenReturn(companyList);

        mockMvc.perform(get("/contentctrl/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Company 1"))
                .andExpect(jsonPath("$[1].name").value("Company 2"));

        verify(companyService, times(1)).getAllCompanies();
    }

    @Test
    void testCreateCompany() throws Exception {
        Company company = new Company();
        company.setName("New Company");

        when(companyService.createCompany(any(Company.class))).thenReturn(company);

        mockMvc.perform(post("/contentctrl/companies")
                .contentType("application/json")
                .content("{\"name\":\"New Company\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Company"));

        verify(companyService, times(1)).createCompany(any(Company.class));
    }

    @Test
    void testGetCompanyById() throws Exception { 
        Company company = new Company();
        company.setName("Company 1");
 
        when(companyService.getCompanyById(1L)).thenReturn(Optional.of(company));
 
        mockMvc.perform(get("/contentctrl/companies/1"))
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.name").value("Company 1"));  
 
        verify(companyService, times(1)).getCompanyById(1L);
    }


    @Test
    void testUpdateCompany() throws Exception { 
        Company updatedCompany = new Company();
        updatedCompany.setName("Updated Company");
 
        when(companyService.updateCompany(eq(1L), any(Company.class))).thenReturn(Optional.of(updatedCompany));
 
        mockMvc.perform(put("/contentctrl/companies/1")
                .contentType("application/json")
                .content("{\"name\":\"Updated Company\"}"))
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.name").value("Updated Company"));  
 
        verify(companyService, times(1)).updateCompany(eq(1L), any(Company.class));
    }


    @Test
    void testDeleteCompany() throws Exception { 
        when(companyService.deleteCompany(1L)).thenReturn(true);   
        mockMvc.perform(delete("/contentctrl/companies/1"))
                .andExpect(status().isOk());  
 
        verify(companyService, times(1)).deleteCompany(1L);
    }
    

}

