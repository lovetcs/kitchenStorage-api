package com.example.kitchenStorage.lovet.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ksEntityDto {
    private String name;
    private String categoryName;
    private Integer quantity;
    private String storedIn;
    private LocalDate expirationDate;
}
