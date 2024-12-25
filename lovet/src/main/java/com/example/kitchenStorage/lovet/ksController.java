package com.example.kitchenStorage.lovet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/items")
@RestController
public class ksController {

    @Autowired
    private ksService ksService;

    @PostMapping("/add")
    public String addItem(@RequestBody ksEntity item){
        ksService.addItem(item);

        return "Item added to Storage.";
    }

    @GetMapping()
    public List<ksEntity> getAllItems(){
        return ksService.getAllItems();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        ksService.removeItem(id);

        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable String id, @RequestBody ksEntity item) {
        ksService.updateItem(id,item);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ksEntity getItem(@PathVariable String id) {
        return ksService.getItem(id);
    }

}
