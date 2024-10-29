package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.model.BucketListItem;
import org.example.backend.service.BucketListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
