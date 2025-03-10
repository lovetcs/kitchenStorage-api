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

    @DeleteMapping("/delete/{ksid}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer ksid) {
        ksService.removeItem(ksid);

        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{ksid}")
    public ResponseEntity<Void> updateItem(@PathVariable Integer ksid, @RequestBody ksEntity item) {
        ksService.updateItem(ksid,item);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{ksid}")
    public ksEntity getItem(@PathVariable Integer ksid) {
        return ksService.getItem(ksid);
    }

}
