package org.example.backend.dto;


import org.example.backend.model.BucketListItemStatus;

public record UpdateBucketListItemRequest(String name, String country, BucketListItemStatus status) {
}
