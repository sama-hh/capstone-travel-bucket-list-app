package org.example.backend.service;

import org.example.backend.dto.UpdateItineraryRequest;
import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.ItineraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Itinerary itinerary = new Itinerary("1", "Trip to Paris", List.of(destinations1), null, null, 1500.0, null, null);
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

    @Test
    void getAllItineraries() {
        //GIVEN
        //WHEN
        itineraryService.getAllItineraries();
        //THEN
        verify(itineraryRepository, times(1)).findAll();
    }

    @Test
    void getItineraryById_shouldReturnItineraryById() {
        //GIVEN
        Destination destinations1 = new Destination("1", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("1", "Trip to Paris", List.of(destinations1),null, null, 1500.0, null, null);
        when(itineraryRepository.findById("1")).thenReturn(Optional.of(itinerary));
        //WHEN
        Itinerary result = itineraryService.getItineraryById("1");
        //THEN
        assertNotNull(result);
        assertEquals("1", result.id());
        assertEquals("Trip to Paris", result.name());
    }

    @Test
    void getItineraryById_shouldReturnNoSuchElementException() {
        // GIVEN
        when(itineraryRepository.findById("2")).thenReturn(Optional.empty());
        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> {
            itineraryService.getItineraryById("2");
        });
    }

    @Test
    void updateItinerary_shouldUpdateItinerary() {
        //GIVEN
        Destination destinations1 = new Destination("1", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("1", "Eiffel Tower", List.of(destinations1), null, null, 1500.0, null, null);
        when(itineraryRepository.findById("1")).thenReturn(Optional.of(itinerary));
        UpdateItineraryRequest updatedItinerary = new UpdateItineraryRequest("Trip to Paris", List.of(destinations1), null, null, 1500.0, null, null);
        //WHEN
        itineraryService.updateItinerary("1", updatedItinerary);
        //THEN
        assertNotNull(updatedItinerary);
        assertEquals("Trip to Paris", updatedItinerary.name());
        assertEquals(1500.00, updatedItinerary.estimatedCost());
    }

    @Test
    void deleteItinerary_shouldDeleteItinerary() {
        //GIVEN
        Destination destinations1 = new Destination("1", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("1", "Eiffel Tower", List.of(destinations1), null, null, 1500.0, null, null);
        when(itineraryRepository.findById("1")).thenReturn(Optional.of(itinerary));
        //WHEN
        itineraryService.deleteItinerary("1");
        //THEN
        verify(itineraryRepository, times(1)).deleteById(itinerary.id());
    }

    @Test
    void deleteItinerary_shouldThrowNoSuchElementException() {
        //GIVEN
        when(itineraryRepository.findById("1")).thenReturn(Optional.empty());
        //WHEN & THEN
        assertThrows(NoSuchElementException.class, () -> itineraryService.deleteItinerary("2"));
        verify(itineraryRepository, never()).deleteById(anyString());
    }
}