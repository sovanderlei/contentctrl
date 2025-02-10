package com.api.contentctrl.controller;
 
import com.api.contentctrl.modal.Branch;
import com.api.contentctrl.service.BranchService;
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

class BranchControllerTest {

    @Mock
    private BranchService branchService;

    @InjectMocks
    private BranchController branchController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(branchController).build();
    }

    @Test
    void testGetAllBranches() throws Exception {
        Branch branch1 = new Branch();
        branch1.setName("Branch 1");

        Branch branch2 = new Branch();
        branch2.setName("Branch 2");

        List<Branch> branchList = Arrays.asList(branch1, branch2);

        when(branchService.getAllBranches()).thenReturn(branchList);

        mockMvc.perform(get("/contentctrl/branches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Branch 1"))
                .andExpect(jsonPath("$[1].name").value("Branch 2"));

        verify(branchService, times(1)).getAllBranches();
    }

    @Test
    void testCreateBranch() throws Exception {
        Branch branch = new Branch();
        branch.setName("New Branch");

        when(branchService.createBranch(any(Branch.class))).thenReturn(branch);

        mockMvc.perform(post("/contentctrl/branches")
                .contentType("application/json")
                .content("{\"name\":\"New Branch\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Branch"));

        verify(branchService, times(1)).createBranch(any(Branch.class));
    }

    @Test
    void testGetBranchById() throws Exception {
        Branch branch = new Branch();
        branch.setName("Branch 1");

        when(branchService.getBranchById(1L)).thenReturn(branch);

        mockMvc.perform(get("/contentctrl/branches/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Branch 1"));

        verify(branchService, times(1)).getBranchById(1L);
    }

    @Test
    void testUpdateBranch() throws Exception {
        Branch updatedBranch = new Branch();
        updatedBranch.setName("Updated Branch");

        when(branchService.updateBranch(eq(1L), any(Branch.class))).thenReturn(updatedBranch);

        mockMvc.perform(put("/contentctrl/branches/1")
                .contentType("application/json")
                .content("{\"name\":\"Updated Branch\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Branch"));

        verify(branchService, times(1)).updateBranch(eq(1L), any(Branch.class));
    }

    @Test
    void testDeleteBranch() throws Exception {
        doNothing().when(branchService).deleteBranch(1L);

        mockMvc.perform(delete("/contentctrl/branches/1"))
                .andExpect(status().isOk());

        verify(branchService, times(1)).deleteBranch(1L);
    }
}

