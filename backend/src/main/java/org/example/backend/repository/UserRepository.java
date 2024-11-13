package org.example.backend.repository;

import org.example.backend.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<AppUser, String> {

}
