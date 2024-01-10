package com.triana.salesianos.edu.satapp.inventariable.service;

import com.triana.salesianos.edu.satapp.inventariable.dto.*;
import com.triana.salesianos.edu.satapp.inventariable.exception.InventariableNotFoundException;
import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.inventariable.repo.InventariableRepository;
import io.jsonwebtoken.io.ParserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventariableService {

    private final InventariableRepository inventariableRepository;

    public List<InventariableDto> getAllInventariable() {
        List<InventariableDto> result = inventariableRepository.findAllInventariable();
        return result;
    }

    public Optional<InventariableDto> getInventariableById(String id) {
        Optional<InventariableDto> result = inventariableRepository.findInventariableById(UUID.fromString(id));
        return result;
    }

    public List<InventariableTypeDto> getAllInventariableTypes() {
        List<InventariableTypeDto> result = inventariableRepository.AllInventariableTypes();
        return result;
    }

    public List<InventariableUbicationDto> getAllInventariableUbications() {
        List<InventariableUbicationDto> result = inventariableRepository.AllInventariableUbications();
        return result;
    }

    public InventariableDto createNewInventariable(CreateInventariableRequest createInventariableRequest) {
        Inventariable newInventariable = Inventariable.builder()
                .id(UUID.randomUUID())
                .name(createInventariableRequest.name())
                .type(createInventariableRequest.type())
                .ubication(createInventariableRequest.ubication())
                .build();
        Inventariable result = inventariableRepository.save(newInventariable);
        return InventariableDto.of(result);
    }

    public InventariableDto EditInventariable(String id, EditInventariableRequest newInventariable) {
        Inventariable inventariable = inventariableRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new InventariableNotFoundException());

        inventariable.setName(newInventariable.name());
        inventariable.setType(newInventariable.type());
        inventariable.setUbication(newInventariable.ubication());

        Inventariable result = inventariableRepository.save(inventariable);

        return InventariableDto.of(result);
    }

    public void deleteInventariable(String id) {
        Inventariable inventariable = inventariableRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new InventariableNotFoundException());
        inventariableRepository.delete(inventariable);
    }
}
