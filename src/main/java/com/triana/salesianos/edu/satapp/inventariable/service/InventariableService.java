package com.triana.salesianos.edu.satapp.inventariable.service;

import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto;
import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableTypeDto;
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
}
