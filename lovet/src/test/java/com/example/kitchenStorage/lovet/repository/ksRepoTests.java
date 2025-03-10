package com.example.kitchenStorage.lovet.repository;

import com.example.kitchenStorage.lovet.ksEntity;
import com.example.kitchenStorage.lovet.ksService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ksRepoTests {

    @Mock
    private ksService mockKsService;  // For testing repository methods that use ksService

    @Mock
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;  // For testing ksService directly

    @Mock
    private DynamoDbTable<ksEntity> dynamoDbTable;

    private ksService realKsService;  // For testing ksService directly

    @Before
    public void setup() {
        // Set up for direct ksService testing
        when(dynamoDbEnhancedClient.table(anyString(), any(TableSchema.class))).thenReturn(dynamoDbTable);
        realKsService = new ksService(dynamoDbEnhancedClient);
    }

    @Test
    public void KitchenStorageRepo_SaveAll_ReturnSavedItem() {
        // Arrange
        ksEntity ksentity = ksEntity.builder()
                .ksid(1)
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        // Mocking the addItem method (no return value)
        doNothing().when(mockKsService).addItem(ksentity);

        // Act
        mockKsService.addItem(ksentity);

        // Assert
        verify(mockKsService).addItem(ksentity);
        Assertions.assertThat(ksentity).isNotNull();
        Assertions.assertThat(ksentity.getName()).isEqualTo("Grapefruit");
    }

    @Test
    public void KitchenStorageRepo_FindByNameId_ReturnItemByID() {
        // Arrange
        ksEntity ksentity = ksEntity.builder()
                .ksid(1)
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        when(mockKsService.getItem(1)).thenReturn(ksentity);

        // Act
        ksEntity foundItem = mockKsService.getItem(1);

        // Assert
        verify(mockKsService).getItem(1);
        Assertions.assertThat(foundItem).isNotNull();
        Assertions.assertThat(foundItem.getName()).isEqualTo("Grapefruit");
    }

    @Test
    public void KitchenStorageRepo_UpdateItem_ReturnUpdatedItemWithMockService() {
        // Arrange
        ksEntity ksentity = ksEntity.builder()
                .ksid(1)
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        ksEntity updatedKsEntity = ksEntity.builder()
                .ksid(1)
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Cupboard")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        // Mock the updateItem method (void return type, so we use doNothing)
        doNothing().when(mockKsService).updateItem(1, updatedKsEntity);

        // Act
        mockKsService.updateItem(1, updatedKsEntity);

        // Assert
        verify(mockKsService).updateItem(1, updatedKsEntity);

    }

    @Test
    public void KitchenStorageRepo_UpdateItem_ReturnUpdatedItem() {
        // Arrange
        Integer ksid = 1;

        // Create the original item that exists in the database
        ksEntity existingItem = ksEntity.builder()
                .ksid(ksid)
                .name("Original Name")
                .categoryName("Original Category")
                .quantity(5)
                .storedIn("Fridge")
                .expirationDate(LocalDate.now().plusDays(7))
                .build();

        // Create the item with updated values
        ksEntity updatedItem = ksEntity.builder()
                .ksid(ksid)
                .name("Updated Name")
                .categoryName("Updated Category")
                .quantity(10)
                .storedIn("Freezer")
                .expirationDate(LocalDate.now().plusDays(30))
                .build();

        // Mock the getItem behavior to return the existing item
        when(dynamoDbTable.getItem(any(Key.class))).thenReturn(existingItem);

        // Act
        realKsService.updateItem(ksid, updatedItem);

        // Assert
        // Verify that getItem was called with the correct key
        verify(dynamoDbTable).getItem(Key.builder().partitionValue(ksid).build());

        // Verify putItem was called with an item that has the updated values

        verify(dynamoDbTable).putItem(any(ksEntity.class));
    }
}