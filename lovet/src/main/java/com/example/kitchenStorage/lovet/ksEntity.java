package com.example.kitchenStorage.lovet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class ksEntity {

    private Long id;
    private String name;
    private String categoryName;
    private Integer quantity;
    private String storedIn;
    private LocalDate expirationDate;

    @DynamoDbPartitionKey
    public Long getId() {
        return id;
    }

}