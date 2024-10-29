package org.example.backend.dto;

import org.example.backend.model.BucketListItem;

public record CreateBucketListItemRequest(String name, String country, String status) {

    public BucketListItem toModel() {
        return BucketListItem.builder()
                .name(name)
                .country(country)
                .status(status)
                .build();
    }

}
