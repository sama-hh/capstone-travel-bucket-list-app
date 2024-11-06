package org.example.backend.service;

import lombok.AllArgsConstructor;
import org.example.backend.model.BucketListItemStatus;
import org.example.backend.repository.BucketListRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class DashboardService {
    private final BucketListRepository bucketListRepository;

    public long getTotalDestinations() {
        return bucketListRepository.count();
    }

    public long getVisitedDestinations() {
        return bucketListRepository.countByStatus(BucketListItemStatus.VISITED);
    }

}
