package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.dto.CreateBucketListItemRequest;
import org.example.backend.model.BucketListItem;
import org.example.backend.repository.BucketListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
