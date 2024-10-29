package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.dto.UpdateBucketListItemRequest;
import org.example.backend.model.BucketListItem;
import org.example.backend.repository.BucketListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BucketListService {
    private final BucketListRepository bucketListRepository;
    private IdService idService;

    public List<BucketListItem> getAllBucketLists() {
        return bucketListRepository.findAll();
    }

    public BucketListItem createBucketListItem(BucketListItem bucketListItem) {
        String bucketListItemId = idService.randomId();

        BucketListItem newBucketListItem = new BucketListItem(bucketListItemId, bucketListItem.name(), bucketListItem.country(), bucketListItem.status());
        return bucketListRepository.save(newBucketListItem);
    }

    public BucketListItem getBucketListItemById(String id) {
        Optional<BucketListItem> bucketListItem = bucketListRepository.findById(id);
        return bucketListItem.orElseThrow(() -> new NoSuchElementException("Bucket list item not found"));
    }

    public BucketListItem updateBucketListItem(String id, UpdateBucketListItemRequest request) {
        BucketListItem bucketListItemToUpdate = bucketListRepository.findById(id).orElse(null);
        if (bucketListItemToUpdate == null) {
            throw new NoSuchElementException("Bucket list item not found");
        } else {
            BucketListItem newBucketListItem = new BucketListItem(bucketListItemToUpdate.id(), request.name(), request.country(), request.status());
            return bucketListRepository.save(newBucketListItem);
        }
    }

}
