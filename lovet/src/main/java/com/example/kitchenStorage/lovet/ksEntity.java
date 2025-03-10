package com.example.kitchenStorage.lovet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class ksEntity {

    private Integer ksid;
    private String name;
    private String categoryName;
    private Integer quantity;
    private String storedIn;
    private LocalDate expirationDate;

    @DynamoDbPartitionKey
    public Integer getKsid() {
        return ksid;
    }

    public void setKsid(Integer ksid) {
        this.ksid = ksid;
    }
}
