package org.example.backend.service;

import org.example.backend.model.BucketListItem;
import org.example.backend.repository.BucketListRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        BucketListItem bucketListItem = new BucketListItem("1", "London", "United Kingdom", "Not Visited");
        when(idService.randomId()).thenReturn("1");
        //WHEN
        service.createBucketListItem(bucketListItem);
        //THEN
        verify(repository, times(1)).save(bucketListItem);
    }

    @Test
    void getBucketListItemById_shouldReturnBucketListItem() {
        //GIVEN
        BucketListItem bucketListItem = new BucketListItem("1", "London", "United Kingdom", "Not Visited");
        when(repository.findById("1")).thenReturn(Optional.of(bucketListItem));
        //WHEN
        BucketListItem result = service.getBucketListItemById("1");
        //THEN
        assertNotNull(result);
        assertEquals("1", result.id());
        assertEquals("London", result.name());
    }

    @Test
    public void getBucketListItemById_shouldReturnNoSuchElementException() {
        // GIVEN
        when(repository.findById("2")).thenReturn(Optional.empty());
        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> {
            service.getBucketListItemById("2");
        });
    }
}