package org.example.backend.repository;

import org.example.backend.model.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItineraryRepository extends MongoRepository<Itinerary, String> {

    @Query(sort = "{ 'createdDate': -1 }")
    Optional<Itinerary> findFirstByOrderByCreatedDateDesc();
}
