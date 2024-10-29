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

    @Test
    @DirtiesContext
    void getAllBucketLists_shouldReturnAllBucketLists() throws Exception {
        BucketListItem item1 = new BucketListItem("1", "London", "United Kingdom", "Not Visited");
        BucketListItem item2 = new BucketListItem("2", "Cape Town", "South Africa", "Not Visited");
        List<BucketListItem> items = List.of(item1, item2);

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
    void createBucketListItem_shouldReturnCreatedBucketListItem() throws Exception {
        BucketListItem item = new BucketListItem("1", "London", "United Kingdom", "Not Visited");
        when(idService.randomId()).thenReturn("1");
        when(bucketListRepository.save(item)).thenReturn(item);

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
}