package com.example.kitchenStorage.lovet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class ksService {

    @Autowired
    private ksRepo ksRepo;

    public List<ksEntity> getAllItems() {
        return ksRepo.findAll();
    }

    public void addItem(ksEntity item) {
        ksRepo.save(item);
    }

    public void removeItem(String id) {

        ksEntity item = ksRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Item"));

        ksRepo.delete(item);
    }

    public ksEntity getItem(String id) {

        ksEntity item = ksRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Item"));

        return item;
    }

    public void updateItem(String id, ksEntity item) {

        ksEntity Olditem = ksRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Item"));

        ksRepo.save(item);

    }
}



}
