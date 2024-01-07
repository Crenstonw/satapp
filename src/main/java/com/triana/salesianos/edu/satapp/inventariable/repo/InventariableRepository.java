package com.triana.salesianos.edu.satapp.inventariable.repo;

import com.triana.salesianos.edu.satapp.inventariable.controller.InventariableController;
import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto;
import com.triana.salesianos.edu.satapp.inventariable.dto.InventariableTypeDto;
import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventariableRepository extends JpaRepository<Inventariable, UUID> {

    @Query("""
            SELECT new com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto(
            i.id,
            i.name,
            i.type,
            i.ubication
            )
            FROM Inventariable i
            """)
    List<InventariableDto> findAllInventariable();

    @Query("""
            SELECT new com.triana.salesianos.edu.satapp.inventariable.dto.InventariableDto(
            i.id,
            i.name,
            i.type,
            i.ubication
            )
            FROM Inventariable i
            WHERE i.id = ?1
            """)
    Optional<InventariableDto> findInventariableById(UUID id);

    @Query("""
            SELECT DISTINCT new com.triana.salesianos.edu.satapp.inventariable.dto.InventariableTypeDto(
            i.type
            )
            FROM Inventariable i
            """)
    List<InventariableTypeDto> AllInventariableTypes();
}
