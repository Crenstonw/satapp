package com.triana.salesianos.edu.satapp.inventariable.dto;

import com.triana.salesianos.edu.satapp.inventariable.modal.Inventariable;
import com.triana.salesianos.edu.satapp.ticket.modal.Ticket;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record InventariableDto(
        UUID id,
        String name,
        String type,
        String ubication

) {
    public static InventariableDto of(Inventariable i) {
        return new InventariableDto(
                i.getId(),
                i.getName(),
                i.getType(),
                i.getUbication()
        );
    }
}
