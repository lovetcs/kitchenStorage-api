package com.example.kitchenStorage.lovet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class ksEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "storedIn")
    private String storedIn;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

}
