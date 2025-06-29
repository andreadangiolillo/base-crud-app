package com.andreadangiolillo.basecrudapp.controllers;

import com.andreadangiolillo.basecrudapp.services.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseCrudController<T, ID> {

    private final CrudService<T, ID> service;

    public BaseCrudController(CrudService<T, ID> service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<T> create(@RequestBody T T) {
        T saved = service.save(T);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T T) {
        T updated = service.update(id, T);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
