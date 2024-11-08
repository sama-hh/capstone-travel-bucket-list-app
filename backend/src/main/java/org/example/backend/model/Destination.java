package org.example.backend.model;

import lombok.Builder;
import lombok.With;

import java.time.LocalDateTime;

@With
@Builder
public record Destination(String destinationId, String destinationName, LocalDateTime arrivalTime, LocalDateTime departureTime) {
}
