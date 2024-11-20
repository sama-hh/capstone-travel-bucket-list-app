package org.example.backend.controller;

import org.example.backend.model.BucketListItem;
import org.example.backend.model.BucketListItemStatus;
import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;
import org.example.backend.service.BucketListService;
import org.example.backend.service.DashboardService;
import org.example.backend.service.ItineraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DashboardControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BucketListService bucketListService;
    @Autowired
    private ItineraryService itineraryService;

    @Test
    void getTotalDestinations() throws Exception {
        Destination destinations = new Destination("1", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("123", "European Vacation", List.of(destinations), null, null, 1000.0, null, null);
        BucketListItem bucketListItem = new BucketListItem("1", "London", "United Kingdom", BucketListItemStatus.NOT_VISITED);

        itineraryService.createItinerary(itinerary);
        bucketListService.createBucketListItem(bucketListItem);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/dashboard/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalDestinations").value(1))
                .andExpect(jsonPath("$.visitedDestinations").value(0))
                .andExpect(jsonPath("$.totalItineraries").value(1))
                .andExpect(jsonPath("$.lastCreatedItinerary.id").exists());

    }

}