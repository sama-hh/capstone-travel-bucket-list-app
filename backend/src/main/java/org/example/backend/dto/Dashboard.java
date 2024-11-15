package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.backend.model.Itinerary;

@Data
@AllArgsConstructor
public class Dashboard {
    private Long totalDestinations;
    private Long visitedDestinations;
    private Long totalItineraries;
    private Itinerary lastCreatedItinerary;
}
