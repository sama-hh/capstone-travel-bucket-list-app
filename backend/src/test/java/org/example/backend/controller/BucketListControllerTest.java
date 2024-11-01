package org.example.backend.controller;

import org.example.backend.model.BucketListItem;
import org.example.backend.repository.BucketListRepository;
import org.example.backend.service.BucketListService;
import org.example.backend.service.IdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class BucketListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BucketListService bucketListService;
    @MockBean
    private BucketListRepository bucketListRepository;
    @MockBean
    private IdService idService;
    @Autowired
    private BucketListController bucketListController;

    @Test
    @DirtiesContext
    void getAllBucketLists_shouldReturnAllBucketLists() throws Exception {
        BucketListItem bucketListItem1 = new BucketListItem("1", "London", "United Kingdom", "Not Visited");
        BucketListItem bucketListItem2 = new BucketListItem("2", "Cape Town", "South Africa", "Not Visited");
        List<BucketListItem> items = List.of(bucketListItem1, bucketListItem2);

        when(bucketListRepository.findAll()).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bucket-lists"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                    {
                                        "id": "1",
                                        "name": "London",
                                        "country": "United Kingdom",
                                        "status": "Not Visited"
                                    },
                                    {
                                        "id": "2",
                                        "name": "Cape Town",
                                        "country": "South Africa",
                                        "status": "Not Visited"
                                    }
                                ]
                                """
                ));
    }

    @Test
    @DirtiesContext
    void createBucketListItem_shouldReturnCreatedBucketListItem() throws Exception {
        BucketListItem bucketListItem = new BucketListItem("1", "London", "United Kingdom", "Not Visited");
        when(idService.randomId()).thenReturn("1");
        when(bucketListRepository.save(bucketListItem)).thenReturn(bucketListItem);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/bucket-lists")
                        .content("""
                                    {
                                        "id": "1",
                                        "name": "London",
                                        "country": "United Kingdom",
                                        "status": "Not Visited"
                                    }
                                """)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("London"));

    }

    @Test
    @DirtiesContext
    void getBucketListItemById_shouldReturnBucketListItem() throws Exception {
        BucketListItem bucketListItem = new BucketListItem("1", "London", "United Kingdom", "Not Visited");
        when(bucketListRepository.findById("1")).thenReturn(Optional.of(bucketListItem));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bucket-lists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("London"));
    }

    @Test
    @DirtiesContext
    void getBucketListItemById_shouldReturnNoSuchElementException() throws Exception {
        when(bucketListRepository.findById("2")).thenThrow(new NoSuchElementException("Bucket list item not found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bucket-lists/2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Bucket list item not found"));
    }

    @Test
    @DirtiesContext
    void updateBucketListItem_shouldUpdateBucketListItem() throws Exception {
        BucketListItem bucketListItem = new BucketListItem("1", "Hamburg", "Germany", "Not Visited");
        String nameToUpdate = "Berlin";
        BucketListItem updatedBucketListItem = new BucketListItem("1", nameToUpdate, "Germany", "Not Visited");

        when(bucketListRepository.findById("1")).thenReturn(Optional.of(bucketListItem));
        when(bucketListRepository.save(updatedBucketListItem)).thenReturn(updatedBucketListItem);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/bucket-lists/1")
                        .content("""
                                   {
                                       "name": "Berlin",
                                       "country": "Germany",
                                       "status": "Not Visited"
                                   }
                                """
                        )
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value(nameToUpdate))
                .andExpect(jsonPath("$.country").value("Germany"))
                .andExpect(jsonPath("$.status").value("Not Visited"));
    }

    @Test
    @DirtiesContext
    void deleteBucketListItem() throws Exception {
        BucketListItem bucketListItem = new BucketListItem("1", "Hamburg", "Germany", "Not Visited");

        when(bucketListRepository.findById("1")).thenReturn(Optional.of(bucketListItem));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bucket-lists/1"))
                .andExpect(status().isOk());
        verify(bucketListRepository).deleteById("1");
    }

}