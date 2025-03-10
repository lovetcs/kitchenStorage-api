
package com.example.kitchenStorage.lovet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ksService {

    private final DynamoDbTable<ksEntity> table;

    @Autowired
    public ksService(DynamoDbEnhancedClient enhancedClient) {
        this.table = enhancedClient.table("kitchenstorage",
                TableSchema.fromBean(ksEntity.class));
    }

    public List<ksEntity> getAllItems() {
        return table.scan().items().stream().collect(Collectors.toList());
    }

    public void addItem(ksEntity item) {
        table.putItem(item);
    }

    public void removeItem(Integer ksid) {
        ksEntity item = getItem(ksid);
        table.deleteItem(Key.builder().partitionValue(ksid).build());
    }

    public ksEntity getItem(Integer ksid) {
        return Optional.ofNullable(table.getItem(Key.builder().partitionValue(ksid).build()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Item"));
    }

    public void updateItem(Integer ksid, ksEntity item) {
        // Fetch the existing entity (simulate the existing entity logic)
        ksEntity existingItem = getItem(ksid);  // Assume this fetches the entity if it exists

        // Update the values from the incoming item
        existingItem.setName(item.getName());
        existingItem.setCategoryName(item.getCategoryName());
        existingItem.setQuantity(item.getQuantity());
        existingItem.setStoredIn(item.getStoredIn());  // This is the field we want to update
        existingItem.setExpirationDate(item.getExpirationDate());

        // Save the updated item back to the table
        table.putItem(existingItem);
    }
}

