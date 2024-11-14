package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.dto.DestinationProgress;
import org.example.backend.dto.ItineraryProgress;
import org.example.backend.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/total-destinations")
    public ResponseEntity<DestinationProgress> getDestinations() {
        Long totalDestinations = dashboardService.getTotalDestinations();
        Long visitedDestinations = dashboardService.getVisitedDestinations();
        DestinationProgress summary = new DestinationProgress(totalDestinations, visitedDestinations);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/total-itineraries")
    public ResponseEntity<ItineraryProgress> getItineraries() {
        Long totalItineraries = dashboardService.getItineraries();
        ItineraryProgress summary = new ItineraryProgress(totalItineraries);
        return ResponseEntity.ok(summary);
    }
}
