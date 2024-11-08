package org.example.backend.dto;

import org.example.backend.model.Destination;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateItineraryRequest(String name,
                                     List<Destination> destinations,
                                     LocalDateTime startDate,
                                     LocalDateTime endDate,
                                     double estimatedCost,
                                     LocalDateTime createdDate,
                                     LocalDateTime lastModifiedDate) {
}
