package org.example.backend.controller;

import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;
import org.example.backend.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user")
class DashboardControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DashboardService dashboardService;

    @Test
    @DirtiesContext
    void getTotalDestinations() throws Exception {
        Destination destinations1 = new Destination("1", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("123", "European Vacation", List.of(destinations1), null, null, 1000.0, null, null);
        when(dashboardService.getTotalDestinations()).thenReturn(10L);
        when(dashboardService.getVisitedDestinations()).thenReturn(3L);
        when(dashboardService.getItineraries()).thenReturn(3L);
        when(dashboardService.getLastCreatedItinerary()).thenReturn(itinerary);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/dashboard/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalDestinations").value(10))
                .andExpect(jsonPath("$.visitedDestinations").value(3))
                .andExpect(jsonPath("$.totalItineraries").value(3))
                .andExpect(jsonPath("$.lastCreatedItinerary.id").value("123"));

    }

}