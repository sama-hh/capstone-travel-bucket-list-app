package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.dto.CreateItineraryRequest;
import org.example.backend.model.Itinerary;
import org.example.backend.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/itineraries")
public class ItineraryController {
    private final ItineraryService itineraryService;

    @PostMapping
    public ResponseEntity<Itinerary> createItinerary(@RequestBody CreateItineraryRequest request) {
        Itinerary createdItinerary = itineraryService.createItinerary(request.toModel());
        System.out.println("Created Itinerary: " + createdItinerary);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItinerary);
    }
}
