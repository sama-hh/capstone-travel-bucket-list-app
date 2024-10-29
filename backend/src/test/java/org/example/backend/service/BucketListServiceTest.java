package org.example.backend.service;

import org.example.backend.model.BucketListItem;
import org.example.backend.repository.BucketListRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class BucketListServiceTest {
    private final BucketListRepository repository = mock(BucketListRepository.class);
    private final IdService idService = mock(IdService.class);
    private final BucketListService service = new BucketListService(repository, idService);

    @Test
    void getAllBucketLists() {
        //GIVEN
        //WHEN
        service.getAllBucketLists();
        //THEN
        verify(repository, times(1)).findAll();
    }

    @Test
    void createBucketListItem() {
        //GIVEN
        BucketListItem item = new BucketListItem("1", "London", "United Kingdom", "Not Visited");
        when(idService.randomId()).thenReturn("1");
        //WHEN
        service.createBucketListItem(item);
        //THEN
        verify(repository, times(1)).save(item);
    }
}