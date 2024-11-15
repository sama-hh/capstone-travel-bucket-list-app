package org.example.backend.service;

import org.example.backend.model.BucketListItemStatus;
import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.BucketListRepository;
import org.example.backend.repository.ItineraryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DashboardServiceTest {
    private final BucketListRepository bucketListRepository = mock(BucketListRepository.class);
    private final ItineraryRepository itineraryRepository = mock(ItineraryRepository.class);
    private final DashboardService service = new DashboardService(bucketListRepository, itineraryRepository);

    @Test
    void getTotalDestinations() {
        //GIVEN
        when(bucketListRepository.count()).thenReturn(3L);
        //WHEN
        long totalDestinations = service.getTotalDestinations();
        //THEN
        assertEquals(3L, totalDestinations);
    }

    @Test
    void getVisitedDestinations() {
        //GIVEN
        when(bucketListRepository.countByStatus(BucketListItemStatus.VISITED)).thenReturn(3L);
        //WHEN
        long totalDestinations = service.getVisitedDestinations();
        //THEN
        assertEquals(3L, totalDestinations);
    }

    @Test
    void getItineraries() {
        //GIVEN
        when(itineraryRepository.count()).thenReturn(3L);
        //WHEN
        long totalItineraries = service.getItineraries();
        //THEN
        assertEquals(3L, totalItineraries);
    }

    @Test
    void getLastCreatedItinerary() {
        //GIVEN
        Destination destinations1 = new Destination("1", "Eiffel Tower", null, null);
        Itinerary itinerary = new Itinerary("123", "European Vacation", List.of(destinations1), null, null, 1000.0, null, null);
        when(itineraryRepository.findFirstByOrderByCreatedDateDesc()).thenReturn(Optional.of(itinerary));
        //WHEN
        Itinerary result = service.getLastCreatedItinerary();
        //THEN
        assertNotNull(result);
        assertEquals("123", result.id());
    }
}