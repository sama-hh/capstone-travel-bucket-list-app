package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.model.BucketListItemStatus;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.BucketListRepository;
import org.example.backend.repository.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DashboardService {
    private final BucketListRepository bucketListRepository;
    private final ItineraryRepository itineraryRepository;

    public long getTotalDestinations() {
        return bucketListRepository.count();
    }

    public long getVisitedDestinations() {
        return bucketListRepository.countByStatus(BucketListItemStatus.VISITED);
    }

    public long getItineraries() {
        return itineraryRepository.count();
    }

    public Itinerary getLastCreatedItinerary() {
        Optional<Itinerary> latestItinerary = itineraryRepository.findFirstByOrderByCreatedDateDesc();
        return latestItinerary.orElseGet(() -> {
            return new Itinerary("", "", null, null, null, 0, null, null);
        });
    }

}
