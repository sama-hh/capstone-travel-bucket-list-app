package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.dto.CreateBucketListItemRequest;
import org.example.backend.dto.UpdateBucketListItemRequest;
import org.example.backend.model.BucketListItem;
import org.example.backend.service.BucketListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bucket-lists")
public class BucketListController {
    private final BucketListService bucketListService;

    @GetMapping
    public List<BucketListItem> getAllBucketLists() {
        return bucketListService.getAllBucketLists();
    }

    @PostMapping
    public BucketListItem createBucketListItem(@RequestBody CreateBucketListItemRequest request) {
        return bucketListService.createBucketListItem(request.toModel());
    }

    @GetMapping("/{id}")
    public BucketListItem getBucketListItemById(@PathVariable String id) {
        return bucketListService.getBucketListItemById(id);
    }

    @PutMapping("/{id}")
    public BucketListItem updateBucketListItem(@PathVariable String id, @RequestBody UpdateBucketListItemRequest request) {
        return bucketListService.updateBucketListItem(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBucketListItem(@PathVariable String id){
        bucketListService.deleteBucketListItem(id);
    }
}
