package org.example.backend.service;

import org.example.backend.model.BucketListItemStatus;
import org.example.backend.repository.BucketListRepository;
import org.example.backend.repository.ItineraryRepository;
import org.junit.jupiter.api.Test;

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
}