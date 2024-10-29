package org.example.backend.repository;

import org.example.backend.model.BucketListItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketListRepository extends MongoRepository<BucketListItem, String>  {
}
