package com.example.kitchenStorage.lovet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ksService {

    private final ksRepo ksRepo;

    @Autowired
    public ksService(ksRepo ksRepo) {
        this.ksRepo = ksRepo;
    }

    public List<ksEntity> getAllItems() {
        return ksRepo.findAll();
    }

    public void addItem(ksEntity item) {
        ksRepo.save(item);
    }

    public void removeItem(String name) {
        ksEntity item = ksRepo.findById(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Item"));
        ksRepo.delete(item);
    }

    public ksEntity getItem(String name) {
        return ksRepo.findById(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Item"));
    }

    public void updateItem(String name, ksEntity item) {
        if (!ksRepo.existsById(name)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Item");
        }
        item.setName(name); // Ensure the name is set correctly
        ksRepo.save(item);
    }
}
