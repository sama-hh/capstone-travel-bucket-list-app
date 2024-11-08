package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private IdService idService;
    private Clock clock;

    public Itinerary createItinerary(Itinerary itinerary) {
        String itineraryId = idService.randomId();
        LocalDateTime now = LocalDateTime.now(clock);

        Itinerary newItinerary = new Itinerary(itineraryId, itinerary.name(), itinerary.destinations(), itinerary.startDate(), itinerary.endDate(), itinerary.estimatedCost(), now, now);
        System.out.println("New Itinerary in Service: " + newItinerary);
        return itineraryRepository.save(newItinerary);
    }
}
