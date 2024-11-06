package org.example.backend.service;

import org.example.backend.model.BucketListItemStatus;
import org.example.backend.repository.BucketListRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DashboardServiceTest {
    private final BucketListRepository repository = mock(BucketListRepository.class);
    private final DashboardService service = new DashboardService(repository);

    @Test
    void getTotalDestinations() {
        //GIVEN
        when(repository.count()).thenReturn(3L);
        //WHEN
        long totalDestinations = service.getTotalDestinations();
        //THEN
        assertEquals(3L, totalDestinations);
    }

    @Test
    void getVisitedDestinations() {
        //GIVEN
        when(repository.countByStatus(BucketListItemStatus.VISITED)).thenReturn(3L);
        //WHEN
        long totalDestinations = service.getVisitedDestinations();
        //THEN
        assertEquals(3L, totalDestinations);
    }
}