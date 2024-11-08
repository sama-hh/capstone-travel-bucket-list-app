package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.dto.UpdateItineraryRequest;
import org.example.backend.model.Itinerary;
import org.example.backend.repository.ItineraryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private IdService idService;

    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    public Itinerary createItinerary(Itinerary itinerary) {
        String itineraryId = idService.randomId();
        LocalDateTime now = LocalDateTime.now();

        Itinerary newItinerary = new Itinerary(itineraryId, itinerary.name(), itinerary.destinations(), itinerary.startDate(), itinerary.endDate(), itinerary.estimatedCost(), now, now);
        System.out.println("New Itinerary in Service: " + newItinerary);
        return itineraryRepository.save(newItinerary);
    }

    public Itinerary getItineraryById(String id) {
        Optional<Itinerary> itinerary = itineraryRepository.findById(id);
        return itinerary.orElseThrow(() -> new NoSuchElementException("Itinerary not found"));
    }

    public Itinerary updateItinerary(String id, UpdateItineraryRequest request) {
        Itinerary itineraryToUpdate = itineraryRepository.findById(id).orElse(null);
        LocalDateTime now = LocalDateTime.now();

        if (itineraryToUpdate == null) {
            throw new NoSuchElementException("Itinerary not found");
        } else {
            Itinerary newItinerary = new Itinerary(itineraryToUpdate.id(), request.name(), request.destinations(), request.startDate(), request.endDate(), request.estimatedCost(), itineraryToUpdate.createdDate(), now);
            return itineraryRepository.save(newItinerary);
        }
    }

    public void deleteItinerary(String id) {
        Optional<Itinerary> itinerary = itineraryRepository.findById(id);

        if (itinerary.isPresent()) {
            itineraryRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Itinerary not found");
        }
    }
}
