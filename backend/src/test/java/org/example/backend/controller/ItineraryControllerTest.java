package org.example.backend.controller;

import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.ItineraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItineraryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ItineraryRepository itineraryRepository;

    @Test
    @DirtiesContext
    void createItinerary_shouldReturnCreatedItinerary() throws Exception {
        Destination destinations1 = new Destination("123", "Eiffel Tower",
                LocalDateTime.of(2024, 12, 8, 12, 30),
                LocalDateTime.of(2024, 12, 9, 12, 30)
        );
        LocalDateTime now = LocalDateTime.now();

        Itinerary itinerary = new Itinerary("1", "European Vacation",
                List.of(destinations1),
                LocalDate.of(2024, 12, 1).atStartOfDay(),
                LocalDate.of(2024, 12, 10).atTime(20, 0),
                1000.0,
                now,
                now
        );

        itineraryRepository.save(itinerary);

        String requestJson = """
                        {
                            "id": "1",
                            "name": "European Vacation",
                            "destinations": [
                                {
                                    "destinationId": "123",
                                    "destinationName": "Eiffel Tower",
                                    "arrivalTime": "2024-12-08T12:30:00",
                                    "departureTime": "2024-12-09T12:30:00"
                                }
                            ],
                            "startDate": "2024-12-01T00:00:00",
                            "endDate": "2024-12-10T20:00:00",
                            "estimatedCost": 1000.0,
                            "createdDate": "2024-12-01T00:00:00",
                            "lastModifiedDate": "2024-12-01T00:00:00"
                        }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/itineraries")
                        .content(requestJson)
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", "application/json"))
                .andDo(result -> {
                    System.out.println("Response content: " + result.getResponse().getContentAsString());
                })
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("European Vacation"))
                .andExpect(jsonPath("$.destinations[0].destinationName").value("Eiffel Tower"));
    }

}