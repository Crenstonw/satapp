package com.triana.salesianos.edu.satapp.inventariable.controller;

import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.inventariable.service.InventariableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InventariableController {

    private final InventariableService inventariableService;

    @GetMapping("/inventariable")
    public ResponseEntity<Inventariable> getAllInventariable() {
        return null;
    }

    @GetMapping("/inventariable/{id}")
    public ResponseEntity<Inventariable> getInventariableById(@PathVariable String id) {
        return null;
    }

    @GetMapping("/inventariable/tipos")
    public ResponseEntity<List<Inventariable>> getInventariableTypes() {
        return null;
    }

    @GetMapping("/inventariable/ubicaciones")
    public ResponseEntity<List<Inventariable>> getInventariableUbications() {
        return null;
    }

    //Solo administradores

    @PostMapping("/inventariable/new")
    public ResponseEntity<Inventariable> newInvetariable() {
        return null;
    }

    @PutMapping("/inventariable/edit/{id}")
    public ResponseEntity<Inventariable> editInventariable(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/inventariable/delete/{id}")
    public ResponseEntity<Inventariable> deleteInventariable(@PathVariable String id) {
        return null;
    }
}
