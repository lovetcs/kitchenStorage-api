/*
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ksController.class)
public class ksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ksService ksservice;

    @Autowired
    private ObjectMapper objectMapper;

    private ksEntity testItem;

    @BeforeEach
    public void init() {
        testItem = ksEntity.builder()
                .name("testItem")
                .categoryName("Test Category")
                .quantity(1)
                .storedIn("Test Location")
                .expirationDate(LocalDate.now().plusDays(7))
                .build();
    }


    @Test
    public void testDeleteItem() throws Exception {

        String itemId = "testItem";
        ksEntity expectedItem = new ksEntity();
        expectedItem.setName(itemId);
        doNothing().when(ksservice).removeItem(itemId);

        mockMvc.perform(delete("/items/delete/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(ksservice, times(1)).removeItem(itemId);

    }

    @Test
    public void testGetItem() throws Exception {
        String itemId = "testItem";
        ksEntity expectedItem = new ksEntity();
        expectedItem.setName(itemId);

        when(ksservice.getItem(itemId)).thenReturn(expectedItem);

        mockMvc.perform(get("/items/{id}", itemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(itemId));
    }

    @Test
    public void testGetAllItems() throws Exception {
        List<ksEntity> mockItems = Arrays.asList(
                ksEntity.builder()
                        .id(1L)
                        .name("Item1")
                        .categoryName("Category1")
                        .quantity(1)
                        .storedIn("Location1")
                        .expirationDate(LocalDate.now())
                        .build(),
                ksEntity.builder()
                        .id(2L)
                        .name("Item2")
                        .categoryName("Category2")
                        .quantity(2)
                        .storedIn("Location2")
                        .expirationDate(LocalDate.now())
                        .build()
        );

        when(ksservice.getAllItems()).thenReturn(mockItems);

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Item1")))
                .andExpect(jsonPath("$[1].name", is("Item2")));

    }

    @Test
    public void testAddItem() throws Exception {

        doNothing().when(ksservice).addItem(any(ksEntity.class));

        mockMvc.perform(post("/items/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testItem)))
                .andExpect(status().isOk())
                .andExpect(content().string("Item added to Storage."));
    }
}
*/