package org.example.backend.controller;

import org.example.backend.model.BucketListItem;
import org.example.backend.model.BucketListItemStatus;
import org.example.backend.repository.BucketListRepository;
import org.example.backend.service.IdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "user")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BucketListControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BucketListRepository bucketListRepository;

    @Test
    void getAllBucketLists_shouldReturnAllBucketLists() throws Exception {
        BucketListItem bucketListItem1 = new BucketListItem("1", "London", "United Kingdom", BucketListItemStatus.NOT_VISITED);
        BucketListItem bucketListItem2 = new BucketListItem("2", "Cape Town", "South Africa", BucketListItemStatus.NOT_VISITED);
        bucketListRepository.save(bucketListItem1);
        bucketListRepository.save(bucketListItem2);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bucket-lists"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                    {
                                        "id": "1",
                                        "name": "London",
                                        "country": "United Kingdom",
                                        "status": "NOT_VISITED"
                                    },
                                    {
                                        "id": "2",
                                        "name": "Cape Town",
                                        "country": "South Africa",
                                        "status": "NOT_VISITED"
                                    }
                                ]
                                """
                ));
    }

    @Test
    void createBucketListItem_shouldReturnCreatedBucketListItem() throws Exception {
        BucketListItem bucketListItem = new BucketListItem("1", "London", "United Kingdom", BucketListItemStatus.NOT_VISITED);
        bucketListRepository.save(bucketListItem);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/bucket-lists")
                        .content("""
                                    {
                                        "id": "1",
                                        "name": "London",
                                        "country": "United Kingdom",
                                        "status": "NOT_VISITED"
                                    }
                                """)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("London"));

    }

    @Test
    void getBucketListItemById_shouldReturnBucketListItem() throws Exception {
        BucketListItem bucketListItem = new BucketListItem("1", "London", "United Kingdom", BucketListItemStatus.NOT_VISITED);
        bucketListRepository.save(bucketListItem);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bucket-lists/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("London"));
    }

    @Test
    void getBucketListItemById_shouldReturnNoSuchElementException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/bucket-lists/2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Bucket list item not found"));
    }

    @Test
    void updateBucketListItem_shouldUpdateBucketListItem() throws Exception {
        BucketListItem bucketListItem = new BucketListItem("1", "Hamburg", "Germany", BucketListItemStatus.NOT_VISITED);
        String nameToUpdate = "Berlin";
        BucketListItem updatedBucketListItem = bucketListItem.withName(nameToUpdate);
        bucketListRepository.save(updatedBucketListItem);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/bucket-lists/1")
                        .content("""
                                   {
                                       "name": "Berlin",
                                       "country": "Germany",
                                       "status": "NOT_VISITED"
                                   }
                                """
                        )
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value(nameToUpdate))
                .andExpect(jsonPath("$.country").value("Germany"))
                .andExpect(jsonPath("$.status").value("NOT_VISITED"));
    }

    @Test
    void deleteBucketListItem() throws Exception {
        BucketListItem bucketListItem = new BucketListItem("1", "Hamburg", "Germany", BucketListItemStatus.NOT_VISITED);
        bucketListRepository.save(bucketListItem);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/bucket-lists/1"))
                .andExpect(status().isOk());
    }

}