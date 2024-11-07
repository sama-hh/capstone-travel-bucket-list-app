package org.example.backend.controller;

import org.example.backend.repository.BucketListRepository;
import org.example.backend.service.DashboardService;
import org.example.backend.service.IdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DashboardControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DashboardService dashboardService;

    @Test
    @DirtiesContext
    void getTotalDestinations() throws Exception {
        when(dashboardService.getTotalDestinations()).thenReturn(10L);
        when(dashboardService.getVisitedDestinations()).thenReturn(3L);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/dashboard/total-destinations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalDestinations").value(10))
                .andExpect(jsonPath("$.visitedDestinations").value(3));
    }

}