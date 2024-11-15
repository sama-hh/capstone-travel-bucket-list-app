package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.dto.Dashboard;
import org.example.backend.model.Itinerary;
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

    @GetMapping("/statistics")
    public ResponseEntity<Dashboard> getDestinations() {
        Long totalDestinations = dashboardService.getTotalDestinations();
        Long visitedDestinations = dashboardService.getVisitedDestinations();
        Long totalItineraries = dashboardService.getItineraries();
        Itinerary lastCreatedItinerary = dashboardService.getLastCreatedItinerary();
        Dashboard summary = new Dashboard(totalDestinations, visitedDestinations, totalItineraries, lastCreatedItinerary);
        return ResponseEntity.ok(summary);
    }

}
