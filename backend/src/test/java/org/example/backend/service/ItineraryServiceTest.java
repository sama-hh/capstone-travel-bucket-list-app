package org.example.backend.service;

import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.BucketListRepository;
import org.example.backend.repository.ItineraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ItineraryServiceTest {
    private final ItineraryRepository repository = mock(ItineraryRepository.class);
    private final IdService idService = mock(IdService.class);
    private final Clock clock = mock(Clock.class);
    private final ItineraryService service = new ItineraryService(repository, idService, clock);

    @Test
    void createItinerary() {
        LocalDateTime fixedNow = LocalDateTime.of(2024, 10, 8, 12, 30);
        Instant instant = fixedNow.atZone(ZoneId.systemDefault()).toInstant();
        when(clock.instant()).thenReturn(instant);
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());

        Destination destinations1 = new Destination("1", "Eiffel Tower",
                LocalDateTime.of(2024, 12, 8, 12, 30),
                LocalDateTime.of(2024, 12, 9, 12, 30)
        );

        Itinerary itinerary = new Itinerary("1", "European Vacation",
                List.of(destinations1),
                LocalDate.of(2024, 12, 1).atStartOfDay(),
                LocalDate.of(2024, 12, 10).atTime(20, 0),
                1000.0,
                fixedNow,
                fixedNow
        );

        when(idService.randomId()).thenReturn("1");
        when(repository.save(itinerary)).thenReturn(itinerary);

        Itinerary result = service.createItinerary(itinerary);

        assertNotNull(result);
        assertEquals("1", result.id());
        verify(repository, times(1)).save(result);
    }
}