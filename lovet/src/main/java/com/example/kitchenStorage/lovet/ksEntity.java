package com.example.kitchenStorage.lovet;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class ksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @JsonProperty("category_name")
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonProperty("stored_in")
    @Column(name = "stored_in")
    private String storedIn;

    @JsonProperty("expiration_date")
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}
