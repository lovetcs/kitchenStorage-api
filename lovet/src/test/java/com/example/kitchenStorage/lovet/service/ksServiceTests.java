package com.example.kitchenStorage.lovet.service;

import com.example.kitchenStorage.lovet.ksEntity;
import com.example.kitchenStorage.lovet.ksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ksServiceTests {

    @Mock
    private DynamoDbEnhancedClient enhancedClient;

    @Mock
    private DynamoDbTable<ksEntity> mockTable;

    private ksService service;

    @BeforeEach
    void setUp() {
        // Set up the mock for the table
        when(enhancedClient.table(anyString(), any(TableSchema.class))).thenReturn(mockTable);

        // Create our service with the mock
        service = new ksService(enhancedClient);
    }

    @Test
    void testAddItem() {
        // Create a simple test item
        ksEntity item = new ksEntity();
        item.setKsid(1);
        item.setName("Test Item");

        // Call the method
        service.addItem(item);

        // Verify the mockTable.putItem was called with our item
        verify(mockTable).putItem(item);
    }

    @Test
    void testGetItem_Success() {
        // Create a test item
        Integer ksid = 1;
        ksEntity item = new ksEntity();
        item.setKsid(ksid);
        item.setName("Test Item");

        // Mock the return value
        when(mockTable.getItem(any(Key.class))).thenReturn(item);

        // Call the method
        ksEntity result = service.getItem(ksid);

        // Verify result
        assertEquals("Test Item", result.getName());
    }

    @Test
    void testGetItem_NotFound() {
        // Mock behavior for item not found
        when(mockTable.getItem(any(Key.class))).thenReturn(null);

        // Call the method and expect exception
        assertThrows(ResponseStatusException.class, () -> {
            service.getItem(999);
        });
    }

    @Test
    void testRemoveItem() {
        // Create a test item
        Integer ksid = 1;
        ksEntity item = new ksEntity();
        item.setKsid(ksid);

        // Mock behavior
        when(mockTable.getItem(any(Key.class))).thenReturn(item);

        // Call the method
        service.removeItem(ksid);

        // Verify deleteItem was called
        verify(mockTable).deleteItem(any(Key.class));
    }

    @Test
    void testUpdateItem() {
        // Create existing item
        Integer ksid = 1;
        ksEntity existingItem = new ksEntity();
        existingItem.setKsid(ksid);
        existingItem.setName("Old Name");

        // Updated item
        ksEntity updatedItem = new ksEntity();
        updatedItem.setName("New Name");

        // Mock behavior
        when(mockTable.getItem(any(Key.class))).thenReturn(existingItem);

        // Call the method
        service.updateItem(ksid, updatedItem);

        // Verify putItem was called
        verify(mockTable).putItem(any(ksEntity.class));
    }
}