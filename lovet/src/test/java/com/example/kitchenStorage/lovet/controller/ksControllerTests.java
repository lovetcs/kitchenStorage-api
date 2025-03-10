package com.example.kitchenStorage.lovet.controller;

import com.example.kitchenStorage.lovet.ksController;
import com.example.kitchenStorage.lovet.ksEntity;
import com.example.kitchenStorage.lovet.ksService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ksController.class)
public class ksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ksService ksService;

    @Autowired
    private ObjectMapper objectMapper;

    private ksEntity testItem;

    @BeforeEach
    public void setUp() {
        // Create a test item
        testItem = ksEntity.builder()
                .ksid(1)  // Changed from id to ksid to match your entity
                .name("Test Item")
                .categoryName("Test Category")
                .quantity(1)
                .storedIn("Test Location")
                .expirationDate(LocalDate.now().plusDays(7))
                .build();
    }

    @Test
    public void testGetAllItems_ShouldReturnAllItems() throws Exception {
        // Arrange Set up test data
        List<ksEntity> mockItems = Arrays.asList(
                ksEntity.builder()
                        .ksid(1)  // Changed from id to ksid
                        .name("Item1")
                        .categoryName("Category1")
                        .quantity(1)
                        .storedIn("Location1")
                        .expirationDate(LocalDate.now())
                        .build(),
                ksEntity.builder()
                        .ksid(2)  // Changed from id to ksid
                        .name("Item2")
                        .categoryName("Category2")
                        .quantity(2)
                        .storedIn("Location2")
                        .expirationDate(LocalDate.now())
                        .build()
        );

        // Tell Mockito what to return when service method is called
        when(ksService.getAllItems()).thenReturn(mockItems);

        // Act & Assert - Perform the request and check the results
        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Item1")))
                .andExpect(jsonPath("$[1].name", is("Item2")));
    }

    @Test
    public void testGetItem_ShouldReturnSingleItem() throws Exception {
        // Arrange
        Integer itemId = 1;  // Changed from String to Integer to match your entity
        when(ksService.getItem(itemId)).thenReturn(testItem);

        // Act & Assert
        mockMvc.perform(get("/items/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Item"));
    }

    @Test
    public void testAddItem_ShouldAddItemAndReturnSuccess() throws Exception {
        // Arrange
        doNothing().when(ksService).addItem(any(ksEntity.class));

        // Act & Assert
        mockMvc.perform(post("/items/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isOk())
                .andExpect(content().string("Item added to Storage."));

        // Verify the service method was called
        verify(ksService).addItem(any(ksEntity.class));
    }

    @Test
    public void testDeleteItem_ShouldDeleteItemAndReturnNoContent() throws Exception {
        // Arrange
        Integer itemId = 1;  // Changed from String to Integer
        doNothing().when(ksService).removeItem(itemId);

        // Act & Assert
        mockMvc.perform(delete("/items/delete/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify the service method was called
        verify(ksService).removeItem(itemId);
    }

    @Test
    public void testUpdateItem_ShouldUpdateItemAndReturnSuccess() throws Exception {
        // Arrange
        Integer itemId = 1;
        ksEntity updatedItem = ksEntity.builder()
                .ksid(itemId)
                .name("Updated Item")
                .categoryName("Updated Category")
                .quantity(5)
                .storedIn("New Location")
                .expirationDate(LocalDate.now().plusDays(14))
                .build();

        doNothing().when(ksService).updateItem(eq(itemId), any(ksEntity.class));

        // Act & Assert
        mockMvc.perform(put("/items/update/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedItem)))
                .andExpect(status().isNoContent());


        // Verify the service method was called
        verify(ksService).updateItem(eq(itemId), any(ksEntity.class));
    }
}