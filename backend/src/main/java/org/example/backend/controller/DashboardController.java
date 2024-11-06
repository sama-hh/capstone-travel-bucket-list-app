package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/total-destinations")
    public ResponseEntity<Map<String, Long>> getTotalDestinations() {
        Map<String, Long> response = Map.of("total-destinations", dashboardService.getTotalDestinations());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/visited-destinations")
    public ResponseEntity<Map<String, Long>> getVisitedDestinations() {
        Map<String, Long> response = Map.of("visited-destinations", dashboardService.getVisitedDestinations());
        return ResponseEntity.ok(response);
    }
}
