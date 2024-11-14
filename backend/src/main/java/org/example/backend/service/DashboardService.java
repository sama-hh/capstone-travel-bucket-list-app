package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.model.BucketListItemStatus;
import org.example.backend.repository.BucketListRepository;
import org.example.backend.repository.ItineraryRepository;
import org.springframework.stereotype.Service;

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
}
