package org.example.backend.model;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BucketListItem(String id, String name, String country, BucketListItemStatus status) {
}
