package com.example.kitchenStorage.lovet.repository;

import com.example.kitchenStorage.lovet.ksEntity;
import com.example.kitchenStorage.lovet.ksRepo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ksRepoTests {

    @Autowired
    private ksRepo ksrepo;

    @Test
    public void KitchenStorageRepo_SaveAll_ReturnSavedItem() {
        // Arrange
        ksEntity ksentity = ksEntity.builder()
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        // Act
        ksEntity savedEntity = ksrepo.save(ksentity);

        // Assert
        Assertions.assertThat(savedEntity).isNotNull();
        Assertions.assertThat(savedEntity.getName()).isEqualTo("Grapefruit");

    }

    @Test
    public void KitchenStorageRepo_GetAll_ReturnMoreThanOneItem() {

        //Arrange
        ksEntity ksentity = ksEntity.builder()
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        ksEntity ksentity2 = ksEntity.builder()
                .name("Apple")
                .categoryName("Fruits")
                .quantity(1)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 10, 31))
                .build();

        ksrepo.save(ksentity);
        ksrepo.save(ksentity2);

        //Act
        List<ksEntity> ksList = ksrepo.findAll();

        //Assert
        Assertions.assertThat(ksList).isNotNull();
        Assertions.assertThat(ksList.size()).isEqualTo(2);

    }

    @Test
    public void KitchenStorageRepo_FindByNameId_ReturnItemByID() {

        //Arrange
        ksEntity ksentity = ksEntity.builder()
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        ksrepo.save(ksentity);

        //Act
        ksEntity ksList = ksrepo.findById(ksentity.getName()).get();

        //Assert
        Assertions.assertThat(ksList).isNotNull();

    }

    @Test
    public void KitchenStorageRepo_UpdateItem_ReturnItemNotNull() {

        //Arrange
        ksEntity ksentity = ksEntity.builder()
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        ksrepo.save(ksentity);
        ksEntity ksItemSave = ksrepo.findById(ksentity.getName()).get();

        ksItemSave.setStoredIn("Cupboard");

        //Act
        ksEntity updatedItem = ksrepo.save(ksItemSave);

        //Assert
        Assertions.assertThat(updatedItem.getStoredIn()).isNotNull();
        Assertions.assertThat(updatedItem.getStoredIn()).isEqualTo("Cupboard");

    }
}
