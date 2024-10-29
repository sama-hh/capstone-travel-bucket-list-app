package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.model.BucketListItem;
import org.example.backend.repository.BucketListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BucketListService {
    private final BucketListRepository bucketListRepository;

    public List<BucketListItem> getAllBucketLists() {
        return bucketListRepository.findAll();
    }
}
