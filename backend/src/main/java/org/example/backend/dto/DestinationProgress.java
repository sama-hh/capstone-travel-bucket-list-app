package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DestinationProgress {
    private Long totalDestinations;
    private Long visitedDestinations;

}
