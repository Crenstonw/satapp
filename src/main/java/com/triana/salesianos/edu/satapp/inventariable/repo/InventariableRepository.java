package com.triana.salesianos.edu.satapp.inventariable.repo;

import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InventariableRepository extends JpaRepository<UUID, Inventariable> {
}
