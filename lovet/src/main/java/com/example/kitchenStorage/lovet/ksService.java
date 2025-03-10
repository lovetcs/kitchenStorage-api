
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
        // Check if item exists
        getItem(ksid); // This will throw an exception if not found
        item.setKsid(ksid);
        table.putItem(item);
    }
}

