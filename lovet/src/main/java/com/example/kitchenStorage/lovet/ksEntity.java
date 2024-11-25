package com.example.kitchenStorage.lovet;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")


public class ksEntity {
    @Id
    private String id;
    private String Category;
    private Integer quantity;
    private LocalDate expirationDate;

}
