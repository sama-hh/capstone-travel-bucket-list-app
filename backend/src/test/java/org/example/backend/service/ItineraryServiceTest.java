package org.example.backend.service;

import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.ItineraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ItineraryServiceTest {
    @Mock
    private ItineraryRepository itineraryRepository;

    @Mock
    private IdService idService;

    @InjectMocks
    private ItineraryService itineraryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createItinerary() {
        //GIVEN
        Destination destinations1 = new Destination("1", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary(null, "Trip to Paris", List.of(destinations1), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(7), 1500.0, null, null);
        String generatedId = "12345";
        when(idService.randomId()).thenReturn(generatedId);
        when(itineraryRepository.save(any(Itinerary.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //WHEN
        Itinerary result = itineraryService.createItinerary(itinerary);

        //THEN
        assertNotNull(result);
        assertEquals(generatedId, result.id());
        assertEquals("Trip to Paris", result.name());
        assertEquals(1500.0, result.estimatedCost());
        assertNotNull(result.createdDate());
        assertNotNull(result.lastModifiedDate());

        verify(idService, times(1)).randomId();
        verify(itineraryRepository, times(1)).save(any(Itinerary.class));
    }
}