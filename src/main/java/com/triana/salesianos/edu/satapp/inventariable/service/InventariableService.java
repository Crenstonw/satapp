package com.triana.salesianos.edu.satapp.inventariable.service;

import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto;
import com.triana.salesianos.edu.satapp.inventariable.repo.InventariableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventariableService {

    private final InventariableRepository inventariableRepository;

    public List<InventariableDto> getAllInventariable() {
        List<InventariableDto> result = inventariableRepository.findAllInventariable();
        return result;
    }
}
