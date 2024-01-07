package com.triana.salesianos.edu.satapp.inventariable.controller;

import com.triana.salesianos.edu.satapp.inventariable.dto.*;
import com.triana.salesianos.edu.satapp.inventariable.exception.InventariableNotFoundException;
import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.inventariable.service.InventariableService;
import com.triana.salesianos.edu.satapp.user.exception.EmptyListException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class InventariableController {

    private final InventariableService inventariableService;

    @GetMapping("/inventariable")
    public ResponseEntity<List<InventariableDto>> getAllInventariable() {
        List<InventariableDto> result = inventariableService.getAllInventariable();
        if(result.isEmpty())
            throw new EmptyListException();
        else
            return ResponseEntity.ok().body(result);
    }

    @GetMapping("/inventariable/{id}")
    public ResponseEntity<Optional<InventariableDto>> getInventariableById(@PathVariable String id) {
        Optional<InventariableDto> result = inventariableService.getInventariableById(id);
        if(result.isPresent())
            return ResponseEntity.ok().body(result);
        else
            throw new InventariableNotFoundException();
    }

    @GetMapping("/inventariable/tipos")
    public ResponseEntity<List<InventariableTypeDto>> getInventariableTypes() {
        List<InventariableTypeDto> result = inventariableService.getAllInventariableTypes();
        if(result.isEmpty())
            throw new EmptyListException();
        else
            return ResponseEntity.ok().body(result);
    }

    @GetMapping("/inventariable/ubicaciones")
    public ResponseEntity<List<InventariableUbicationDto>> getInventariableUbications() {
        List<InventariableUbicationDto> result = inventariableService.getAllInventariableUbications();
        if(result.isEmpty())
            throw new EmptyListException();
        else
            return ResponseEntity.ok().body(result);
    }

    //Solo administradores

    @PostMapping("/inventariable/new")
    public ResponseEntity<InventariableDto> newInvetariable(@RequestBody CreateInventariableRequest createInventariableRequest) {
        InventariableDto inventariable = inventariableService.createNewInventariable(createInventariableRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventariable);
    }

    @PutMapping("/inventariable/edit/{id}")
    public ResponseEntity<InventariableDto> editInventariable(
            @PathVariable String id,
            @RequestBody EditInventariableRequest editInventariableRequest)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventariableService.EditInventariable(id, editInventariableRequest));
    }

    @DeleteMapping("/inventariable/delete/{id}")
    public ResponseEntity<Inventariable> deleteInventariable(@PathVariable String id) {
        return null;
    }
}
