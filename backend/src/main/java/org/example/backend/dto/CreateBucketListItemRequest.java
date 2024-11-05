package org.example.backend.dto;

import org.example.backend.model.BucketListItem;
import org.example.backend.model.BucketListItemStatus;

public record CreateBucketListItemRequest(String name, String country, BucketListItemStatus status) {

    public BucketListItem toModel() {
        return BucketListItem.builder()
                .name(name)
                .country(country)
                .status(status)
                .build();
    }

}
