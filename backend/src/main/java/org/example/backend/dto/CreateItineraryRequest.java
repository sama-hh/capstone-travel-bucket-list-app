package org.example.backend.dto;

import org.example.backend.model.Destination;
import org.example.backend.model.Itinerary;

import java.time.LocalDateTime;
import java.util.List;

public record CreateItineraryRequest(String name,
                                     List<Destination> destinations,
                                     LocalDateTime startDate,
                                     LocalDateTime endDate,
                                     double estimatedCost,
                                     LocalDateTime createdDate,
                                     LocalDateTime lastModifiedDate) {

    public Itinerary toModel() {
        return Itinerary.builder()
                .name(name)
                .destinations(destinations)
                .startDate(startDate)
                .endDate(endDate)
                .estimatedCost(estimatedCost)
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }

}
