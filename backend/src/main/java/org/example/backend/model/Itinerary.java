package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;

@With
@Builder
public record Itinerary(String id,
                        String name,
                        List<Destination> destinations,
                        LocalDateTime startDate,
                        LocalDateTime endDate,
                        double estimatedCost,
                        LocalDateTime createdDate,
                        LocalDateTime lastModifiedDate) {
}
