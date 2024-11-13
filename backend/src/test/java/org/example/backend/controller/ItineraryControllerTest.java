package org.example.backend.controller;

import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.ItineraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user")
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
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("European Vacation"))
                .andExpect(jsonPath("$.destinations[0].destinationName").value("Eiffel Tower"));
    }

    @Test
    @DirtiesContext
    void getAllItineraries_shouldReturnAllItineraries() throws Exception {
        Destination destinations1 = new Destination("123", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("1", "European Vacation", List.of(destinations1), null, null, 1000.0, null, null);

        itineraryRepository.save(itinerary);

        String requestJson = """
                        [{
                            "id": "1",
                            "name": "European Vacation",
                            "destinations": [
                                {
                                    "destinationId": "123",
                                    "destinationName": "Eiffel Tower",
                                    "arrivalTime": null,
                                    "departureTime": null
                                }
                            ],
                            "startDate": null,
                            "endDate": null,
                            "estimatedCost": 1000.0,
                            "createdDate": null,
                            "lastModifiedDate": null
                        }]
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries"))
                .andExpect(status().isOk())
                .andExpect(content().json(requestJson));
    }

    @Test
    void getItineraryById_shouldReturnItineraryById() throws Exception {
        Destination destinations1 = new Destination("123", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("1", "European Vacation", List.of(destinations1), null, null, 1000.0, null, null);

        itineraryRepository.save(itinerary);

        String requestJson = """
                        {
                            "id": "1",
                            "name": "European Vacation",
                            "destinations": [
                                {
                                    "destinationId": "123",
                                    "destinationName": "Eiffel Tower",
                                    "arrivalTime": null,
                                    "departureTime": null
                                }
                            ],
                            "startDate": null,
                            "endDate": null,
                            "estimatedCost": 1000.0,
                            "createdDate": null,
                            "lastModifiedDate": null
                        }
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(requestJson));

    }

    @Test
    @DirtiesContext
    void getItineraryById_shouldReturnNoSuchElementException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/itineraries/2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Itinerary not found"));
    }

    @Test
    void updateItinerary_shouldReturnUpdatedItinerary() throws Exception {
        Destination destinations1 = new Destination("123", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("1", "European Vacation", List.of(destinations1), null, null, 1000.0, null, null);

        itineraryRepository.save(itinerary);

        String requestJson = """
                        {
                            "id": "1",
                            "name": "European Vacation Summer",
                            "destinations": [
                                {
                                    "destinationId": "123",
                                    "destinationName": "Eiffel Tower",
                                    "arrivalTime": null,
                                    "departureTime": null
                                }
                            ],
                            "startDate": null,
                            "endDate": null,
                            "estimatedCost": 1000.0,
                            "createdDate": null,
                            "lastModifiedDate": null
                        }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/api/itineraries/1")
                        .content(requestJson)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("European Vacation Summer"));
    }

    @Test
    void deleteItinerary_shouldReturnDeletedItinerary() throws Exception {
        Destination destinations1 = new Destination("123", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("1", "European Vacation", List.of(destinations1), null, null, 1000.0, null, null);

        itineraryRepository.save(itinerary);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/itineraries/1"))
                .andExpect(status().isOk());

        Optional<Itinerary> deletedItinerary = itineraryRepository.findById("1");
        assertTrue(deletedItinerary.isEmpty());
    }
}