/*
package com.example.kitchenStorage.lovet.service;

import com.example.kitchenStorage.lovet.dto.ksEntityDto;
import com.example.kitchenStorage.lovet.ksEntity;
import com.example.kitchenStorage.lovet.ksRepo;
import com.example.kitchenStorage.lovet.ksService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ksServiceTests {

    @Mock
    private ksRepo ksrepo;

    @InjectMocks
    private ksService ksservice;

    @Test
    public void ksService_FindItem_FindsItemByID() {
        ksEntity ksentity = ksEntity.builder()
                .name("Grapefruit")
                .categoryName("Alcohol")
                .quantity(3)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024,12,12))
                .build();

        when(ksrepo.findById("Grapefruit")).thenReturn(Optional.of(ksentity));

        ksEntity result = ksservice.getItem(1);

        verify(ksrepo, times(1)).findById("Grapefruit");
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo("Grapefruit");

    }

    @Test
    public void ksService_DeleteItem_DeletesItem() {
        ksEntity ksentity = ksEntity.builder()
                .name("Grapefruit")
                .categoryName("Alcohol")
                .quantity(3)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024,12,12))
                .build();

        when(ksrepo.findById("Grapefruit")).thenReturn(Optional.of(ksentity));

        ksservice.removeItem("Grapefruit");

        verify(ksrepo, times(1)).delete(ksentity);

    }


    @Test
    public void ksSerice_GetAllItems_ReturnsItemsInList(){
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
                .quantity(2)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        List<ksEntity> expectedList = Arrays.asList(ksentity, ksentity2);

        when(ksrepo.findAll()).thenReturn(expectedList);

        List<ksEntity> result = ksservice.getAllItems();

        // Assert
        verify(ksrepo, times(1)).findAll();
        assertNotNull(result);
        assertEquals(2, result.size());


    }
    @Test
    public void ksService_UpdateItem_UpdatesItem() {
        ksEntity ksentity = ksEntity.builder()
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();

        when(ksrepo.existsById("Grapefruit")).thenReturn(true);
        when(ksrepo.save(Mockito.any(ksEntity.class))).thenReturn(ksentity);

        ksentity.setStoredIn("Cupboard");

        ksservice.updateItem("Grapefruit", ksentity);

        verify(ksrepo, times(1)).existsById("Grapefruit");
        verify(ksrepo, times(1)).save(ksentity);

    }

    @Test
    public void ksService_CreateItem_SavesItem() {

        ksEntity ksentity = ksEntity.builder()
                .name("Grapefruit")
                .categoryName("Fruits")
                .quantity(10)
                .storedIn("Fridge")
                .expirationDate(LocalDate.of(2024, 12, 31))
                .build();



        when(ksrepo.save(Mockito.any(ksEntity.class))).thenReturn(ksentity);

        ksservice.addItem(ksentity);
        //Act
        verify(ksrepo, times(1)).save(ksentity);


    }

}
*/