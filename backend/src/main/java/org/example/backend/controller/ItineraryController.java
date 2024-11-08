package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.dto.CreateItineraryRequest;
import org.example.backend.dto.UpdateItineraryRequest;
import org.example.backend.model.Itinerary;
import org.example.backend.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/itineraries")
public class ItineraryController {
    private final ItineraryService itineraryService;

    @GetMapping
    public List<Itinerary> getAllItineraries() {
        return itineraryService.getAllItineraries();
    }

    @PostMapping
    public ResponseEntity<Itinerary> createItinerary(@RequestBody CreateItineraryRequest request) {
        Itinerary createdItinerary = itineraryService.createItinerary(request.toModel());
        System.out.println("Created Itinerary: " + createdItinerary);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItinerary);
    }

    @GetMapping("/{id}")
    public Itinerary getItineraryById(@PathVariable String id) {
        return itineraryService.getItineraryById(id);
    }

    @PutMapping("/{id}")
    public Itinerary updateItinerary(@PathVariable String id, @RequestBody UpdateItineraryRequest request) {
        return itineraryService.updateItinerary(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteItinerary(@PathVariable String id){
        itineraryService.deleteItinerary(id);
    }
}
